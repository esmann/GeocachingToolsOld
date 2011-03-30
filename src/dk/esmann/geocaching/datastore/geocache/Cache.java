package dk.esmann.geocaching.datastore.geocache;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 3/26/11
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Cache
{
    @PrimaryKey
    @Persistent
    private String code;

    @Persistent
    private String placedBy;

    @Persistent
    private Boolean archived;

    @Persistent
    private Integer cacheId;

    @Persistent
    private String cacheType;

    @Persistent
    private Date changed;

    @Persistent
    private String container;

    @Persistent
    private String country;

    @Persistent
    private Integer difficulty;

    @Persistent
    private String latitude;

    @Persistent
    private String longitude;

    @Persistent
    private String longHTML;

    @Persistent
    private Integer ownerId;

    @Persistent
    private String ownerName;

    @Persistent
    private Date placedDate;

    @Persistent
    private String shortHtml;

    @Persistent
    private String Symbol;

    @Persistent
    private Boolean temporaryDisabled;

    @Persistent
    private Integer terrain;

    @Persistent
    private Date created;

    public Cache() {}

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getPlacedBy()
    {
        return placedBy;
    }

    public void setPlacedBy(String placedBy)
    {
        this.placedBy = placedBy;
    }

    public Boolean getArchived()
    {
        return archived;
    }

    public void setArchived(Boolean archived)
    {
        this.archived = archived;
    }

    public Integer getCacheId()
    {
        return cacheId;
    }

    public void setCacheId(Integer cacheId)
    {
        this.cacheId = cacheId;
    }

    public String getCacheType()
    {
        return cacheType;
    }

    public void setCacheType(String cacheType)
    {
        this.cacheType = cacheType;
    }

    public Date getChanged()
    {
        return changed;
    }

    public void setChanged(Date changed)
    {
        this.changed = changed;
    }

    public String getContainer()
    {
        return container;
    }

    public void setContainer(String container)
    {
        this.container = container;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public Integer getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLongHTML()
    {
        return longHTML;
    }

    public void setLongHTML(String longHTML)
    {
        this.longHTML = longHTML;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId = ownerId;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public Date getPlacedDate()
    {
        return placedDate;
    }

    public void setPlacedDate(Date placedDate)
    {
        this.placedDate = placedDate;
    }

    public String getShortHtml()
    {
        return shortHtml;
    }

    public void setShortHtml(String shortHtml)
    {
        this.shortHtml = shortHtml;
    }

    public String getSymbol()
    {
        return Symbol;
    }

    public void setSymbol(String symbol)
    {
        Symbol = symbol;
    }

    public Boolean getTemporaryDisabled()
    {
        return temporaryDisabled;
    }

    public void setTemporaryDisabled(Boolean temporaryDisabled)
    {
        this.temporaryDisabled = temporaryDisabled;
    }

    public Integer getTerrain()
    {
        return terrain;
    }

    public void setTerrain(Integer terrain)
    {
        this.terrain = terrain;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }
}
