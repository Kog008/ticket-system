package de.koegler85.model;

import javax.persistence.Entity;

/**
 * <p>
 *     Special type of <code>AbstractUser</code>. <code>userType</code> is set per default via constructor.
 * </p>
 *
 * @see AbstractUser
 * @see UserType
 *
 * @author Gabriel Kögler
 */
@Entity
public class Customer extends AbstractUser
{
    public Customer ()
    {
        setUserType ( UserType.CUSTOMER );
    }
}
