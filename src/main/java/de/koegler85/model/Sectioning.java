package de.koegler85.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The <code>sectioning</code> calculates depending on <code>location</code> and <code>event</code>
 * the different sections and its tickets with all meta information.
 * Basically <code>sectioning</code> represents a collection of Section objects.
 * Section is automatically instantiated by calling <code>sectioning</code>. Section is subclass of sectioning,
 * because it has to know the underlying <code>event</code> to access the <code>eventType</code>.
 *
 * <ul>
 *     <li>
 *         A sport event has only GALLERY sections, because it leaves the STANDINGS, LOGE,
 *         or ORCHESTRA seats for the sporting area.
 *     </li>
 *     <li>
 *         A concert event, for example a rock concert, has instead of LOGE and ORCHESTRA seats a STANDING area
 *         with technically 1 row but much more "seats".
 *     </li>
 *     <li>
 *         A stage event, like theatre, has 1x LOGE, 1x ORCHESTRA and the rest GALLERY
 *     </li>
 * </ul>
 * </p>
 *
 * @see Event
 * @see SectionType
 * @see Section
 * @see Ticket
 *
 * @author Gabriel Kögler
 */
@Embeddable
public class Sectioning
{
    @Autowired
    private Event event;

    private List<Section> sections = new ArrayList<> ();

    public Sectioning ()
    {
        // depending on eventType
        switch ( event.getEventType () )
        {
            case SPORT:
                // LOGE and ORCHESTRA sections in basement are missing ==> only GALLERY ==> numberOfSections - 2
                for (int i = 0; i < event.getLocation ().getNumberOfSections () - 2; i++)
                {
                    Section s = new Section ();
                    s.setSectionType ( SectionType.GALLERY );

                    sections.add ( s );
                }
                break;

            case CONCERT:
                // STANDING section instead of LOGE and ORCHESTRA in basement ==> numberOfSections -1

                // STANDINGs hinzugefügt.
                Section standing = new Section ();
                standing.setSectionType ( SectionType.STANDING );
                sections.add ( standing );

                // STANDINGs already added ==> number of sections left to add numberOfSections -2
                for ( int i = 0; i < event.getLocation ().getNumberOfSections () -2; i++ )
                {
                    Section sec = new Section ();
                    sec.setSectionType ( SectionType.GALLERY );
                    sections.add ( sec );
                }
                break;

            case STAGE:
                // all natural sections appear ==> LOGE, ORCHESTRA and ( numberOfSections -2 ) x GALLERY

                // adding LOGE to list<section>
                Section loge = new Section ();
                loge.setSectionType ( SectionType.LOGE );
                sections.add ( loge );

                // adding ORCHESTRA to list<section>
                Section orchestra = new Section ();
                orchestra.setSectionType ( SectionType.ORCHESTRA );
                sections.add ( orchestra );

                // adding the left GALLERY sections to list<section>
                for ( int i = 0; i < event.getLocation ().getNumberOfSections () - 2; i++ )
                {
                    Section gallery = new Section ();
                    gallery.setSectionType ( SectionType.GALLERY );
                    sections.add ( gallery );
                }
                break;

            default:
                System.out.println ("Error in calculation sectioning");
                break;
        }

        // after instanciation of every section object it has to be filled with tickets for booking
        for (Section s : sections)
        {
            s.createTickets ();
        }
    }

    /**
     * <p>
     *     A section is one compartment of a location. It can be a STANDING, GALLERY, ORCHESTRA or LOGE compartment.
     *     It has <code>numberOfRows</code> and <code>rowCapacity</code>. Both short values are used to generate
     *     a ticket matrix, which holds the sellable ticket instances. Unlike to the list of section objects the
     *     tickets are not automatically instanciated. You need to explicitly call <code>createTickets()</code>.
     * </p>
     */
    private class Section
    {
        // ticket matrix with direct access for booking and reservation
        private Ticket[][] tickets;

        // Enum SectionType for calculating the ticket cost and hence the booking cost.
        private SectionType sectionType;

        // for clean code reasons we save the count of rows and seats in between using following variables.
        private Short rows;
        private Short seats;

        /*
            Because the <<sectionType>> is only held by the sectioning the section constructor itself
            cannot use it to calculate tickets initially.
            Therefor it creates only the reservation matrix <<tickets>>
            Sectioning have to call the calculation funktion of tickets to fill the matrix with objects.
         */
        public Section()
        {
            rows = event.getLocation ().getNumberOfRowsInSection ();
            seats = event.getLocation ().getNumberOfSeatsInRow ();

            tickets = new Ticket[rows][seats];
        }

        public SectionType getSectionType ()
        {
            return sectionType;
        }

        public void setSectionType ( SectionType sectionType )
        {
            this.sectionType = sectionType;
        }

        public void createTickets()
        {
            OUTER_LOOP:
            for (short rowNumber = 0; rowNumber < rows; rowNumber++)
            {
                INNER_LOOP:
                for( short seatNumber = 0; seatNumber < seats; seatNumber++ )
                {
                    BigDecimal cost = event.getBasicEntraceFee ().multiply ( sectionType.getCostFactor () );
                    Ticket ticket = new Ticket ( rowNumber, seatNumber, cost );
                    tickets[rowNumber][seatNumber] = ticket;
                }
            }
        }
    }
}