package de.koegler85.model;

/**
 * <p>
 *     The user's representation types. A user of the ticket website can be either a promoter,
 *     or a customer. It is represented by a String.
 * </p>
 *
 * @author Gabriel Kögler.
 */
public enum UserType
{
    PROMOTER("promoter"),
    CUSTOMER("customer");

    private String userType;

    private UserType(String userType)
    {
        this.userType = userType;
    }

    public String getUserType ()
    {
        return userType;
    }

    @Override
    public String toString ()
    {
        return userType;
    }

    public String getName()
    {
        return this.name ();
    }
}
