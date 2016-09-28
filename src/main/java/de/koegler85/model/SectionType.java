package de.koegler85.model;

/**
 * Class <code>SectionType</code> holds the different possible types of sections every location can has.
 * Each SectionType is connected to a factor, which can be used later to calculate ticket values
 * for booking.
 *
 * @author Gabriel Kögler
 */
public enum SectionType
{
    STANDING ( 1 ),
    ORCHESTRA ( 1 ),
    GALLERY ( 2 ),
    LOGE ( 5 );

    private Integer costFactor;

    SectionType ( Integer factor )
    {
        costFactor = factor;
    }

    public Integer getCostFactor()
    {
        return this.costFactor;
    }

    @Override
    public String toString ()
    {
        return this.name ();
    }
}
