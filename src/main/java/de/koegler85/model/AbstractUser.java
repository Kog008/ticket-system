package de.koegler85.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * <p>
 *     Users are the visitors of our website. In general they can browse the locations and its events.
 *     AbstractUser is the parent object to promoter and customer.
 * </p>
 *
 * @author Gabriel Kögler
 */
public abstract class AbstractUser
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long userId;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private LocalDate birthday;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;
}
