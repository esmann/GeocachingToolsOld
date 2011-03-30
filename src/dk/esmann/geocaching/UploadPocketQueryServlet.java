package dk.esmann.geocaching;

import dk.esmann.geocaching.xml.GPXHandler;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.SAXException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 3/27/11
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadPocketQueryServlet extends HttpServlet
{
    private Logger log = Logger.getLogger(UploadPocketQueryServlet.class.getName());

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
                    log.warning("Got an uploaded file: " + item.getFieldName() + ", name = " + item.getName());

                    int len;
                    byte[] buffer = new byte[8192];

                    System.out.println("received: ");
                    ZipInputStream zipInputStream = new ZipInputStream(stream);
                    ZipEntry zipEntry;
                    while ((zipEntry = zipInputStream.getNextEntry()) != null)
                    {
                        log.info("unzipping: " + zipEntry.getName());
                        if (zipEntry.getName().endsWith("wpts.gpx"))
                        {
                            log.info("Handling wpts file");
                        } else
                        {
                            log.info("Handling cache file");
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


            GPXHandler handler = new GPXHandler(zipInputStream);

    }

}
