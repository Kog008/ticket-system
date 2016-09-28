package de.koegler85.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * <p>
 *     <code>Events</code> are promoted by a - suprise - a <code>promoter</code>. They take place in a
 *     <code>location</code>. The location's sectioning depends on the <code>eventType</code>.
 *     An event holds beside its obvious database id
 *     <ul>
 *         <li>a name</li>
 *         <li>a a text description</li>
 *         <li>a corresponding location</li>
 *         <li>date and time</li>
 *         <li>and the eventType</li>
 *     </ul>
 *     The <code>location</code> can be used at the same time for both, database mapping and information purposes.
 * </p>
 *
 * @see Location
 * @see EventType
 *
 * @author Gabriel Kögler
 */
@Entity
public class Event
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long eventId;

    @Size( min = 5, max = 100, message = "ERROR: '${validatedValue}' has wrong pattern size - must be between {min} and {max}" )
    @NotEmpty
    @Column( unique = true )
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    @Temporal ( TemporalType.TIMESTAMP )
    private LocalDateTime date;

    @Enumerated( EnumType.STRING )
    private EventType eventType;

    @Embedded
    private Sectioning sectioning;
    /*
                +---------------------------+
                |                           |
                |   database relations      |
                |                           |
                +---------------------------+
     */
    /*
        An event takes place in exactly one location, but a location hosts many events.
        event : location
     */
    @ManyToOne
    @JoinColumn( name = "location" )
    private Location location;


    //region Getter and Setter
    public String getName ()
    {
        return name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription ( String description )
    {
        this.description = description;
    }

    public LocalDateTime getDate ()
    {
        return date;
    }

    public void setDate ( LocalDateTime date )
    {
        this.date = date;
    }

    public EventType getEventType ()
    {
        return eventType;
    }

    public void setEventType ( EventType eventType )
    {
        this.eventType = eventType;
    }

    public Sectioning getSectioning ()
    {
        return sectioning;
    }

    public void setSectioning ( Sectioning sectioning )
    {
        this.sectioning = sectioning;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation ( Location location )
    {
        this.location = location;
    }
    //endregion
}