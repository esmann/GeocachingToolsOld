package dk.esmann.geocaching;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 4/3/11
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class markerInfoExclustionStrategy implements ExclusionStrategy
{
    public boolean shouldSkipClass(Class<?> clazz)
    {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f)
    {
        return f.getName().equalsIgnoreCase("longhtml") || f.getName().equalsIgnoreCase("shortHtml");
    }
}