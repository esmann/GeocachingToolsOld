package dk.esmann.geocaching;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.esmann.geocaching.datastore.Cache;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 4/2/11
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadCachesServlet extends HttpServlet

{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //TODO add bounding box
        PersistenceManager pm = PMF.get().getPersistenceManager();
        String query = "select from " + Cache.class.getName();
        @SuppressWarnings("unchecked")
        List<Cache> caches = (List<Cache>) pm.newQuery(query).execute();

        Gson gson = new GsonBuilder().setExclusionStrategies(new markerInfoExclustionStrategy()).create();
        String jsonData = gson.toJson(caches); // TODO Do not fetch/convert ALL data for the caches
        GsonBuilder gsonBuilder = new GsonBuilder();
        response.getWriter().write(jsonData);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        doPost(request, response);
    }
}




