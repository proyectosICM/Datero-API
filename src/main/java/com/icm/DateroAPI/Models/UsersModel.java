package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Represents a user within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The first name of the user.
     */
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    /**
     * The DNI (identification document) of the user.
     */
    @NotBlank(message = "DNI cannot be blank")
    @Size(max = 20, message = "DNI cannot exceed 20 characters")
    private String dni;

    /**
     * Indicates whether the user is currently active or inactive.
     * This is used to determine when and how to display it.
     */
    private Boolean status;

    /**
     * The username of the user.
     */
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 50, message = "Username cannot exceed 50 characters")
    private String username;

    /**
     * The password of the user.
     */
    @NotBlank(message = "Password cannot be blank")
    @Size(max = 50, message = "Password cannot exceed 50 characters")
    private String password;

    /**
     * The role associated with this user.
     */
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    private RolesModel rolesModel;

    /**
     * The company associated with this user.
     */
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private CompaniesModel companiesModel;

    /**
     * Date and time when this ticket was created.
     */
    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneId.of("America/Lima"));

    /**
     * Date and time when this ticket was last updated.
     */
    @Column(name = "updatedAt")
    @UpdateTimestamp
    private ZonedDateTime updatedAt = ZonedDateTime.now(ZoneId.of("America/Lima"));
}