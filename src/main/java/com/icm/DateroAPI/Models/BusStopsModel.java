package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents a bus stop within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bus_stops")
public class BusStopsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The name of the bus stop.
     */
    private String name;

    /**
     * Indicates whether the bus stop is currently active or inactive.
     */
    private Boolean status;

    /**
     * The longitude coordinate of the bus stop.
     */
    @Column(precision = 20, scale = 15)
    private BigDecimal longitude;

    /**
     * The latitude coordinate of the bus stop.
     */
    @Column(precision = 20, scale = 15)
    private BigDecimal latitude;

    /**
     * The district associated with this bus stop.
     */
    @ManyToOne
    @JoinColumn(name = "district", referencedColumnName = "id", nullable = false)
    private DistrictsModel districtsModel;
}