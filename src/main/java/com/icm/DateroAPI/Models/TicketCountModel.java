package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Represents a ticket count within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ticket_counts")
public class TicketCountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The count of tickets.
     */
    private Integer count;

    /**
     * The date of the count.
     */
    private LocalDate day;

    /**
     * The total accumulated amount.
     */
    @Column(nullable = true)
    private Double totalAccumulated;

    /**
     * The ticket associated with this count.
     */
    @ManyToOne
    @JoinColumn(name = "ticket", referencedColumnName = "id", nullable = false)
    private TicketsModel ticketsModel;

    /**
     * The bus associated with this count.
     */
    @ManyToOne
    @JoinColumn(name = "bus", referencedColumnName = "id", nullable = false)
    private BusesModel busesModel;

    /**
     * The company associated with this count.
     */
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private CompaniesModel companiesModel;
}