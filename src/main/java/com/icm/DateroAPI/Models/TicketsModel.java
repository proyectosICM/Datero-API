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
 * Represents a ticket within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tickets")
public class TicketsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The name of the ticket.
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    /**
     * The value of the ticket.
     */
    private String value;

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

    /**
     * The route associated with this ticket.
     */
    @ManyToOne
    @JoinColumn(name = "route", referencedColumnName = "id", nullable = false)
    private RoutesModel routesModel;

    /**
     * The company associated with this ticket.
     */
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private CompaniesModel companiesModel;
}
