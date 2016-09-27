package de.koegler85.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
 * @see Sectioning
 * @see Section
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

    @Column( unique = true )
    private String name;

    @Embedded
    private Address address;

    private String description;

    private Sectioning sectioning;
}
