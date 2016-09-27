package de.koegler85.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *<p>
 *  Reuseable address object for injection to users like Promoter and customer,
 *  but without a relationship in the relational database model.
 * </p>
 *
 * @see AbstractUser
 *
 * @author Gabriel Kögler
 */
@Embeddable
public class Address implements Serializable
{
    /* declaration of fields - the interessting part */
    private String street;
    private Integer postalCode;
    private String city;

    //region boilerplate code: constructor, equals(), hashCode() and getter & setter

    /**
     * <p>
     *     A classic constructor, which sets every member of the class.
     * </p>
     *
     * @param street        part of the address which gathers name of street and corresponding house number at one
     *                      <code>String</code> object
     * @param postalCode    the postal code as 5 ciphers as <code>Integer</code> object
     * @param city          city name as <code>String</code> object
     */
    public Address ( String street, Integer postalCode, String city )
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

    /**
     * <p>
     *     Overrides the <code>toString</code> method from <code>object</code> class.
     * </p>
     *
     * @return  A formatted String to display all member of this class
     */
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

    public Integer getPostalCode ()
    {
        return postalCode;
    }

    public void setPostalCode ( Integer postalCode )
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
