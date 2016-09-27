package de.koegler85.model;

/**
 * <p>
 *     Possible types of events. Determine the <code>sectioning</code> of a <code>location</code> and therefor
 *     the amount sellable tickets.
 * </p>
 *
 * @see Event
 *
 * @author Gabriel Kögler
 */
public enum EventType
{
    SPORT,
    STAGE,
    CONCERT;

    @Override
    public String toString ()
    {
        return this.name ();
    }
}
