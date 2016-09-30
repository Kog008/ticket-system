package de.koegler85.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The <code>Sectioning</code> class calculates depending on the location the eventType the corresponding location's
 * sections and the total amount of sellable tickets.
 *
 * * <ul>
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
 *
 * @author Gabriel Kögler
 */
@Embeddable
public class Sectioning
{
    @Autowired
    private Event event;

    private List<Section> sections = new ArrayList<> ();


    /**
     * The sectioning calculates depending on location and event the total amount of tickets.
     *
     */
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
    }

    /**
     * <p>
     *     A section is one compartment of a location. It can be a STANDING, GALLERY, ORCHESTRA or LOGE compartment.
     *     It has <code>numberOfRows</code> and <code>rowCapacity</code>
     * </p>
     */
    private class Section
    {
        private Ticket[][] tickets;

        private SectionType sectionType;

        private Short rows;
        private Short seats;

        /*
            Because the <<sectionType>> is only by held by the sectioning the section constructor itself
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
            for (int i = 0; i < rows; i++)
            {
                INNER_LOOP:
                for( int j = 0; j < seats; j++ )
                {
                    BigDecimal cost = event.getBasicEntraceFee ().multiply ( sectionType.getCostFactor () );
                    Ticket ticket = new Ticket ( rows, seats, cost );
                }
            }
        }
    }
}