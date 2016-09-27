package de.koegler85.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
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

    @NotEmpty
    @Column( unique = true )
    private String name;

    private String description;

    /*
        An event takes place in exactly one location, but a location hosts many events.
        event : location
     */
    @ManyToOne
    private Location location;

    @NotEmpty
    @Temporal ( TemporalType.TIMESTAMP )
    private LocalDateTime date;

    @Enumerated( EnumType.STRING )
    private EventType eventType;
}