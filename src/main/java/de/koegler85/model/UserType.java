package de.koegler85.model;

/**
 * <p>
 *     The user's representation types. A user of the ticket website can be either a <code>promoter</code>,
 *     or a <code>customer</code>. This enums toggles during user instanciation its type. It is represented by a
 *     <code>String</code>
 * </p>
 *
 * @see AbstractUser
 * @see Promoter
 * @see Customer
 *
 * @author Gabriel Kögler.
 */
public enum UserType
{
    PROMOTER("Promoter"),
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
