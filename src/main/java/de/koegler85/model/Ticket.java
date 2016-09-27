package de.koegler85.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
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
     * @param cost          ticket cost for calculating <code>totalCost</code> in booking
     *
     * @see Booking TODO
     */
    public Ticket ( Short rowNumber, Short seatNumber, BigDecimal cost )
    {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.cost = cost;
    }
}
