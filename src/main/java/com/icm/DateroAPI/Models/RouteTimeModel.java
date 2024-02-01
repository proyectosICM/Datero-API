package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the time information associated with a route in the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "route_time")
public class RouteTimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The expected pass for the route.
     */
    private String expectedPass;

    /**
     * The actual time of the pass for the route.
     */
    private String actualPassTime;

    /**
     * The route associated with this time information.
     */
    @ManyToOne
    @JoinColumn(name = "route", referencedColumnName = "id", nullable = false)
    private RoutesModel routesModel;

    /**
     * The bus associated with this time information.
     */
    @ManyToOne
    @JoinColumn(name = "bus", referencedColumnName = "id", nullable = false)
    private BusesModel busesModel;

    /**
     * The bus stop associated with this time information.
     */
    @ManyToOne
    @JoinColumn(name = "bus_stop", referencedColumnName = "id", nullable = false)
    private BusStopsModel busStopsModel;
}