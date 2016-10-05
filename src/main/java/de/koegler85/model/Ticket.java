package de.koegler85.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * <p>
 *     Finally, the ticket is main object of interest, a customer wants to buy. It has a row and seat number.
 *     More information are not necessary, because the object is only accessible among the sectioning and its
 *     collection of section objects.
 * </p>
 *
 * @see Sectioning
 * @see SectionType
 *
 * @author Gabriel Kögler
 */
@Embeddable
public class Ticket
{
    private Short rowNumber;
    private Short seatNumber;
    private BigDecimal cost;

    /**
     * A classic constructor to set every class member.
     *
     * @param rowNumber     number of row in section at location
     * @param seatNumber    number of seat in row
     * @param cost          ticket cost for calculating <code>totalCost</code> in booking;
     *                      when calculating the cost value consider the correct <code>sectionType</code>
     *
     */
    public Ticket ( Short rowNumber, Short seatNumber, BigDecimal cost )
    {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.cost = cost;
    }
}
