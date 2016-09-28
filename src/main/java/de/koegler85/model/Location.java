package de.koegler85.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 *     A <code>Location</code> represents all information according to the place, where an <code>Event</code> is hosted.
 *     This information are
 *     <ul>
 *         <li>name</li>
 *         <li>description</li>
 *         <li>address</li>
 *         <li>sections</li>
 *     </ul>
 *     The <code>name</code> should be some kind of natural identifier and is configured to be unique.
 *     Address is again embedded from <code>Address</code>, like in <code>AbstractUser</code>.
 *     <code>Description</code> holds a short textual description to the <code>location</code>.
 *     Furthermore there are of course the eventId for database purposes and the member for relational mapping. TODO
 * </p>
 * <p>
 *     A location needs to declare a data structure for describing its sectioning. This means the number and type
 *     of sections hosted by the location. What kind of sections are needed is determinde by the <code>eventType</code>.
 *     Every <code>Location</code> has a gallery with certain amount of <code>sections</code>. Depending on the
 *     <code>eventType</code> there are furthermore sections in the basement:
 *     <tb>
 *         <tr>
 *             <td></td>                <td>sport event</td>    <td>stage event</td>    <td>concert event</td>
 *             <td>hasStanding</td>     <td>false</td>          <td>false</td>          <td>true</td>
 *             <td>hasLoge</td>         <td>false</td>          <td>true</td>           <td>false</td>
 *             <td>hasOrchestra</td>    <td>false</td>          <td>true</td>           <td>false</td>
 *             <td>hasGallery</td>      <td>true</td>           <td>true</td>           <td>true</td>
 *         </tr>
 *     </tb>
 * </p>
 *
 * @author Gabriel Kögler
 *
 * @see Event
 * @see EventType
 * @see Sectioning
 */
@Entity
public class Location
{
    /**
     * class members
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long eventId;

    @Size( min = 5, max = 100, message = "ERROR: '${validatedValue}' has wrong pattern size - must be between {min} and {max}" )
    @NotEmpty
    @Column( length = 15, unique = true )
    private String name;

    @Embedded
    @NotEmpty
    private Address address;

    /*
        Information for sectioning and calculating the amount of tickets.

        Total amount of possible section of seats, depending on the location.
        A stadion for example can have 100 sections, a town hall only 5.
        It is an user option to choose for.
     */
    @Size( min = 1, max = 100, message = "ERROR: Must be between {min} and {max}." )
    @NotEmpty
    @Column( name = "sections" )
    private Short numberOfSections;

    @Size( min = 1, max = 50, message = "ERROR: Must be between {min} and {max}." )
    @NotEmpty
    @Column( name = "rows in section" )
    private Short numberOfRowsInSection;

    @Size( min = 1, max = 100, message = "ERROR: Must be between {min} and {max}." )
    @NotEmpty
    @Column( name = "seats in row" )
    private Short numberOfSeatsInRow;

    /*
                +---------------------------+
                |                           |
                |   database relations      |
                |                           |
                +---------------------------+
     */
    /*
        An event takes place in exactly one location || a location hosts many events.
     */
    @OneToMany( mappedBy = "location" )
    private List<Event> events;

    //region Getter and Setter
    public String getName ()
    {
        return name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress ( Address address )
    {
        this.address = address;
    }

    public Short getNumberOfSections ()
    {
        return numberOfSections;
    }

    public void setNumberOfSections ( Short numberOfSections )
    {
        this.numberOfSections = numberOfSections;
    }

    public Short getNumberOfRowsInSection ()
    {
        return numberOfRowsInSection;
    }

    public void setNumberOfRowsInSection ( Short numberOfRowsInSection )
    {
        this.numberOfRowsInSection = numberOfRowsInSection;
    }

    public Short getNumberOfSeatsInRow ()
    {
        return numberOfSeatsInRow;
    }

    public void setNumberOfSeatsInRow ( Short numberOfSeatsInRow )
    {
        this.numberOfSeatsInRow = numberOfSeatsInRow;
    }

    public List<Event> getEvents ()
    {
        return events;
    }

    public void setEvents ( List<Event> events )
    {
        this.events = events;
    }
    //endregion
}