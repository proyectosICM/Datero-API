package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Represents a bus within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "buses")
public class BusesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The model of the bus.
     */
    private String model;

    /**
     * The license plate of the bus.
     */
    private String plate;

    /**
     * The longitude coordinate of the bus.
     */
    @Column(precision = 20, scale = 15)
    private BigDecimal longitude;

    /**
     * The latitude coordinate of the bus.
     */
    @Column(precision = 20, scale = 15)
    private BigDecimal latitude;

    /**
     * Indicates whether the bus is currently active or inactive.
     */
    private Boolean status;

    /**
     * The user associated with this bus.
     */
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = true)
    private UsersModel usersModel;

    /**
     * The company associated with this bus.
     */
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private CompaniesModel companiesModel;

    /**
     * The route associated with this bus.
     */
    @ManyToOne
    @JoinColumn(name = "route", referencedColumnName = "id", nullable = true)
    private RoutesModel routesModel;
}