package de.koegler85.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *<p>
 *  Reuseable address object for injection to promoter and customer,
 *  but without a relationship in the relational database model
 * </p>
 *
 * @author Gabriel Kögler
 */
@Embeddable
public class Address implements Serializable
{
    /* declaration of fields - the interessting part */
    private String street;
    private Short postalCode;
    private String city;

    //region boilerplate code: constructor, equals(), hashCode() and getter & setter
    public Address ( String street, Short postalCode, String city )
    {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public boolean equals ( Object o )
    {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Address address = (Address) o;

        if (!street.equals ( address.street )) return false;
        if (!postalCode.equals ( address.postalCode )) return false;
        return city.equals ( address.city );

    }

    @Override
    public int hashCode ()
    {
        int result = street.hashCode ();
        result = 31 * result + postalCode.hashCode ();
        result = 31 * result + city.hashCode ();
        return result;
    }

    @Override
    public String toString ()
    {
        return String.format ( "%s, %5d %s", street, postalCode, city );
    }

    //region Getter & Setter
    public String getStreet ()
    {
        return street;
    }

    public void setStreet ( String street )
    {
        this.street = street;
    }

    public Short getPostalCode ()
    {
        return postalCode;
    }

    public void setPostalCode ( Short postalCode )
    {
        this.postalCode = postalCode;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity ( String city )
    {
        this.city = city;
    }
    //endregion
    //endregion
}
