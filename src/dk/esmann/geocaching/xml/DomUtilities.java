package dk.esmann.geocaching.xml;

import com.google.appengine.api.datastore.Text;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 4/2/11
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DomUtilities
{
    private static final SimpleDateFormat placedDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static Element getElement(Element element, String name)
    {
        NodeList elementList = element.getElementsByTagName(name);
        return (Element) elementList.item(0);
    }

    private static String getValue(Element element, String name)
    {
        NodeList elementList = element.getElementsByTagName(name);
        Element subElement = (Element) elementList.item(0);
        NodeList subElementList = subElement.getChildNodes();
        return (subElementList.item(0)).getNodeValue();
    }

    public static String getString(Element element, String name)
    {
        return getValue(element, name);
    }

    public static Text getText(Element element, String name)
    {
        return new Text(getValue(element, name));
    }

    public static Date getDate(Element element, String name) throws ParseException
    {
        String value = getValue(element, name);
        return placedDateFormat.parse(value);
    }

    public static Integer getInteger(Element element, String name)
    {
        String value = getValue(element, name);
        return Integer.parseInt(value, 10);
    }

    public static Float getFloat(Element element, String name)
    {
        String value = getValue(element, name);
        return Float.parseFloat(value);
    }

}
