package de.koegler85.model;

import java.math.BigDecimal;

/**
 * <p>
 * Class <code>SectionType</code> holds the different possible types of sections every location can has.
 * Each <code>SectionType</code> is connected to a factor, which can be used later to calculate ticket values
 * for booking. <<STANDING>> and <<ORCHESTRA>> have no extra charge, but only the base entrance fee.
 * Cost for tickets in <<GALLERY>> are twice and <<LOGE>> five times greater.
 * </p>
 *
 * @author Gabriel Kögler
 */
public enum SectionType
{
    STANDING ( new BigDecimal ( 1 ) ),
    ORCHESTRA ( new BigDecimal ( 1 ) ),
    GALLERY ( new BigDecimal ( 2 ) ),
    LOGE ( new BigDecimal ( 5 ) );

    private BigDecimal costFactor;

    SectionType ( BigDecimal factor )
    {
        costFactor = factor;
    }

    public BigDecimal getCostFactor()
    {
        return this.costFactor;
    }

    @Override
    public String toString ()
    {
        return this.name ();
    }
}
