package dk.esmann.geocaching.xml;

import dk.esmann.geocaching.PMF;
import dk.esmann.geocaching.datastore.geocache.Cache;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.jdo.PersistenceManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Stack;
import java.util.zip.ZipInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 3/27/11
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class GPXHandler extends DefaultHandler
{
    Integer tagCount = 0;
    private Logger logger = Logger.getLogger(GPXHandler.class.getName());
    String characters;
    private Stack<Cache> cacheStack;
    private static final SimpleDateFormat placedDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    Integer dateErrorCount = 0;
    Integer codeErrorsCount = 0;

    public GPXHandler(ZipInputStream zipInputStream)
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try
        {
            SAXParser parser = factory.newSAXParser();
            parser.parse(zipInputStream, this);
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public void startDocument()
    {
        cacheStack = new Stack<Cache>();
    }

    public void startElement(String uri, String localName, String rawName, Attributes attributes)
    {
        if (rawName.equals("wpt"))
        {

            Cache cache = new Cache();
            cache.setLatitude(attributes.getValue("lat"));
            cache.setLongitude(attributes.getValue("lon"));
            cacheStack.push(cache);
        }
    }

    public void endElement(String namespaceURI, String simpleName, String qualifiedName)
    {
        if (!cacheStack.isEmpty())
        {
            Cache cache = cacheStack.pop();
            if (qualifiedName.equals("name"))
            {
                if (!characters.startsWith("GC"))
                {
                    logger.info("broken GC code: " + characters);
                    codeErrorsCount++;
                }
                cache.setCode(characters);
            }
            if (qualifiedName.equals("time"))
            {
                try
                {
                cache.setPlacedDate(placedDateFormat.parse(characters));
                } catch (ParseException e)
                {
                    dateErrorCount++;
                    logger.log(Level.WARNING, "error parsing placedByDate: " + characters);
                }


            }
            cacheStack.push(cache);
        }
    }


    public void characters(char buf[], int offset, int len)
    {
        characters = new String(buf, offset, len);
    }

    public void endDocument()
    {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        while (!cacheStack.isEmpty())
        {
            Cache cache = cacheStack.pop();
            try
            {
                pm.makePersistent(cache);
            } catch (Exception e)
            {
                logger.log(Level.SEVERE, "unable to persist cache", e);
            }
        }
        pm.close();
        System.out.println("There were " + dateErrorCount + " dateErrors.");
        System.out.println("There were " + codeErrorsCount + " codeErrors.");
    }
}
