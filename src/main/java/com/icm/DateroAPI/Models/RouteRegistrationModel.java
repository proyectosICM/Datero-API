package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Represents a route registration within the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route_registrations")
public class RouteRegistrationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The date of the route registration.
     */
    private LocalDate day;

    /**
     * The expected time of arrival.
     */
    @Temporal(TemporalType.TIME)
    private Time expectedArrivalTime;

    /**
     * The actual time of arrival.
     */
    @Temporal(TemporalType.TIME)
    private Time actualArrivalTime;

    /**
     * The company associated with this route registration.
     */
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private CompaniesModel companiesModel;

    /**
     * The route associated with this route registration.
     */
    @ManyToOne
    @JoinColumn(name = "route", referencedColumnName = "id", nullable = false)
    private RoutesModel routesModel;

    /**
     * The bus associated with this route registration.
     */
    @ManyToOne
    @JoinColumn(name = "bus", referencedColumnName = "id", nullable = false)
    private BusesModel busesModel;

    /**
     * The bus stop associated with this route registration.
     */
    @ManyToOne
    @JoinColumn(name = "bus_stop", referencedColumnName = "id", nullable = false)
    private BusStopsModel busStopsModel;
}