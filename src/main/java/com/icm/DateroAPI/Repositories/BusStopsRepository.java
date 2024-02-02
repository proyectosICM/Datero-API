package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.BusStopsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to Bus Stops entities.
 * This repository provides methods for retrieving bus stop records from the system.
 */
@Repository
public interface BusStopsRepository extends JpaRepository<BusStopsModel, Long> {
    /**
     * Retrieves a list of bus stops based on the status.
     *
     * @param status Boolean value indicating the status of the bus stops to retrieve.
     * @return List of BusStopsModel objects associated with the specified status.
     */
    List<BusStopsModel> findByStatus(Boolean status);

    /**
     * Retrieves a paginated list of bus stops based on the status.
     *
     * @param status   Boolean value indicating the status of the bus stops to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of BusStopsModel objects associated with the specified status.
     */
    Page<BusStopsModel> findByStatus(Boolean status, Pageable pageable);
}
