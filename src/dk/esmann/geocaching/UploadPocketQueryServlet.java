package dk.esmann.geocaching;

import dk.esmann.geocaching.datastore.geocache.Cache;
import dk.esmann.geocaching.xml.DomUtilities;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 3/27/11
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadPocketQueryServlet extends HttpServlet
{
    private Logger logger = Logger.getLogger(UploadPocketQueryServlet.class.getName());

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            ServletFileUpload upload = new ServletFileUpload();
            response.setContentType("text/plain");

            FileItemIterator iterator = upload.getItemIterator(request);
            while (iterator.hasNext())
            {
                FileItemStream item = iterator.next();
                InputStream stream = item.openStream();

                if (!item.isFormField())
                {
                    logger.warning("Got an uploaded file: " + item.getFieldName() + ", name = " + item.getName());

                    int len;
                    byte[] buffer = new byte[8192];

                    System.out.println("received: ");
                    ZipInputStream zipInputStream = new ZipInputStream(stream);
                    ZipEntry zipEntry;
                    while ((zipEntry = zipInputStream.getNextEntry()) != null)
                    {
                        logger.info("unzipping: " + zipEntry.getName());
                        if (zipEntry.getName().endsWith("wpts.gpx"))
                        {
                            logger.info("Handling wpts file");
                        } else
                        {
                            logger.info("Handling cache file");
                            handleCacheFile(zipInputStream);
                        }
                    }
                }
            }
        } catch (Exception ex)
        {
            //throw new ServletException(ex);
        }
    }

    private void handleCacheFile(ZipInputStream zipInputStream)
    {
        //int len;
        //byte[] buffer = new byte[8192];
        //
        //try
        //{
        //    while ((len = zipInputStream.read(buffer, 0, buffer.length)) != -1)
        //    {
        //        System.out.write(buffer);
        //        //response.getOutputStream().write(buffer, 0, len);
        //    }
        //} catch (IOException ioex)
        //{
        //    log.warning("unable to read zipfile");
        //}

        Document doc = null;
        String strError = null;
        ArrayList<Cache> caches = new ArrayList<Cache>();

        try
        {
            Integer waypointCounter;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(zipInputStream);
            NodeList waypoints = doc.getElementsByTagName("wpt");

            for (waypointCounter = 0; waypointCounter < waypoints.getLength(); waypointCounter++)
            {
                Node waypoint = waypoints.item(waypointCounter);
                if (waypoint.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element waypointElement = (Element) waypoint;
                    Element groundSpeakElement = DomUtilities.getElement(waypointElement, "groundspeak:cache");

                    Cache cache = new Cache();
                    cache.setLatitude(waypointElement.getAttribute("lat"));
                    cache.setLongitude(waypointElement.getAttribute("lon"));
                    cache.setCode(DomUtilities.getString(waypointElement, "name"));
                    cache.setPlacedDate(DomUtilities.getDate(waypointElement, "time"));
                    cache.setArchived(Boolean.parseBoolean(groundSpeakElement.getAttribute("archived")));
                    cache.setCacheId(Integer.parseInt(groundSpeakElement.getAttribute("id"), 10));
                    cache.setCacheType(DomUtilities.getString(groundSpeakElement, "groundspeak:type"));
                    cache.setContainer(DomUtilities.getString(groundSpeakElement, "groundspeak:container"));
                    cache.setDifficulty(DomUtilities.getFloat(groundSpeakElement, "groundspeak:difficulty"));
                    cache.setTerrain(DomUtilities.getFloat(groundSpeakElement, "groundspeak:terrain"));
                    cache.setTemporaryDisabled(!Boolean.parseBoolean(groundSpeakElement.getAttribute("available")));
                    cache.setCountry(DomUtilities.getString(groundSpeakElement, "groundspeak:country"));
                    cache.setShortHtml(DomUtilities.getString(groundSpeakElement, "groundspeak:short_description"));
                    cache.setLongHTML(DomUtilities.getText(groundSpeakElement, "groundspeak:long_description"));
                    cache.setOwnerName(DomUtilities.getString(groundSpeakElement, "groundspeak:owner")); //TODO get id which is an attribute on the owner element
                    caches.add(cache);
                }
            }
            System.out.println("found " + waypointCounter + " waypoints");
            PersistenceManager pm = PMF.get().getPersistenceManager();
            pm.makePersistentAll(caches);
            return;
        } catch (Exception e)
        {
            logger.log(Level.WARNING, "fail: ", e);
            strError = e.toString();
        }

    }

}
