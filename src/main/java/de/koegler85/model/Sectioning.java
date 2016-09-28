package de.koegler85.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Embeddable;
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
        switch ( event.getEventType () )
        {
            case SPORT:
                // only GALLERY, section LOGE and section ORCHESTRA are missing ==> numberOfSections - 2
                for (int i = 0; i < event.getLocation ().getNumberOfSections () - 2; i++)
                {
                    Section s = new Section ();
                    s.setSectionType ( SectionType.GALLERY );

                    sections.add ( s );
                }
                break;
            case CONCERT:
                break;
            case STAGE:
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

        public Section()
        {
            tickets = new Ticket
                    [event.getLocation ().getNumberOfRowsInSection ()]
                    [event.getLocation ().getNumberOfSeatsInRow ()];
        }

        public SectionType getSectionType ()
        {
            return sectionType;
        }

        public void setSectionType ( SectionType sectionType )
        {
            this.sectionType = sectionType;
        }
    }
}
