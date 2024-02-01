package com.icm.DateroAPI.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a relationship between a route and a bus stop within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "route_bus_stop")
public class RouteBusStopModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The order of the bus stop in the route.
     */
    private Integer order;

    /**
     * The state of the relationship between the route and the bus stop.
     */
    private Boolean status;

    /**
     * The route associated with this relationship.
     */
    @ManyToOne
    @JoinColumn(name = "route", referencedColumnName = "id", nullable = false)
    private RoutesModel routesModel;

    /**
     * The bus stop associated with this relationship.
     */
    @ManyToOne
    @JoinColumn(name = "bus_stop", referencedColumnName = "id", nullable = false)
    private BusStopsModel busStopsModel;
}
