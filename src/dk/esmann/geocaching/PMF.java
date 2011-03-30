package dk.esmann.geocaching;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 3/30/11
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */

public final class PMF
{
    private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF()
    {
    }

    public static PersistenceManagerFactory get()
    {
        return pmfInstance;
    }
}