package de.koegler85.model;

import javax.persistence.Embeddable;

/**
 * <p>
 *     A section is one compartment of a location. It can be a STANDING, GALLERY, ORCHESTRA or LOGE compartment.
 *     It has <code>numberOfRows</code> and <code>rowCapacity</code>
 * </p>
 * @author Gabriel Kögler
 */
@Embeddable
public class Section
{
    // number of rows depends on the section type
    private Integer numberOfRows;

    // number of seats in a row
    private Integer rowCapacity;

    private Ticket[][] numberOfTickets;

    public Section( SectionType sectionType )
    {
        numberOfTickets = new Ticket[numberOfRows][rowCapacity];
    }
}
