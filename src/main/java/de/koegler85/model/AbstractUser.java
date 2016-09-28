package de.koegler85.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * <p>
 *     Users are the visitors of our website. In general they can browse the locations and its events.
 *     <code>AbstractUser</code> is the parent object to Promoter and customer. When instanciating an user the
 *     <code>userType</code> toggles between the possible users.
 * </p>
 * <p>
 *     This class holds all identifying information for both user types and of course the type itself.
 *     <ul>
 *         <li>email</li>
 *         <li>name</li>
 *         <li>birthday</li>
 *         <li>password</li>
 *         <li>address</li>
 *         <li>userType</li>
 *     </ul>
 *     Because <code>promoter</code> and <code>customer</code> have different relations in RDBM the members depending
 *     to that mapping are implemented in the concrete model classes. Only the <code>userId</code> for database
 *     issues is already implemented in this class, because both, promoter and customer, need it.
 *     During <code>email</code> is the natural identity userId is the identity in the database.
 * </p>
 *
 * @see Promoter
 * @see Customer
 * @see Address
 * @see UserType
 *
 * @author Gabriel Kögler
 */
@Entity
@Table( name = "User" )
/*
    An abstract class can be an entity either. All subclasses will be mapped in one table, as long as they are entities
    too.
 */
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
//@DiscriminatorValue( "userType" )
public abstract class AbstractUser
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long userId;

    @NotEmpty( message = "ERROR: mandatory field" )
    @Email( message = "ERROR: malformed email address")
    @Column(unique = true, nullable = false )
    private String email;

    @NotBlank( message = "ERROR: mandatory field" )
    @Size( min = 5, max = 20, message = "ERROR: '${validatedValue}' has wrong pattern size - must be between {min} and {max}" )
    @Pattern ( regexp = "^[a-zA-Z0-9_]+$", message = "ERROR: malformed user name" )
    private String name;

    @Temporal (TemporalType.DATE )
    @NotEmpty( message = "ERROR: mandatory field" )
    private LocalDate birthday;

    @NotBlank( message = "ERROR: mandatory field" )
    @Size( min = 6, max = 20, message = "ERROR: between {min} and {max} characters are required" )
    @Column(nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    //region Getter and Setter
    public String getEmail ()
    {
        return email;
    }

    public void setEmail ( String email )
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }

    public LocalDate getBirthday ()
    {
        return birthday;
    }

    public void setBirthday ( LocalDate birthday )
    {
        this.birthday = birthday;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword ( String password )
    {
        this.password = password;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress ( Address address )
    {
        this.address = address;
    }

    public UserType getUserType ()
    {
        return userType;
    }

    protected void setUserType ( UserType userType )
    {
        this.userType = userType;
    }
    //endregion

    //region equals() and hashCode()
    @Override
    public boolean equals ( Object o )
    {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        AbstractUser that = (AbstractUser) o;

        return userId.equals ( that.userId );

    }

    @Override
    public int hashCode ()
    {
        return userId.hashCode ();
    }
    //endregion
}
