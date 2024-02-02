package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.RouteBusStopModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to RouteBusStopModel entities.
 * This repository provides methods for retrieving route bus stop records from the system.
 */
@Repository
public interface RouteBusStopRepository extends JpaRepository<RouteBusStopModel, Long> {

    /**
     * Retrieves a list of route bus stops based on the status.
     *
     * @param status Boolean value indicating the status of the route bus stops to retrieve.
     * @return List of RouteBusStopModel objects associated with the specified status.
     */
    List<RouteBusStopModel> findByStatus(Boolean status);

    /**
     * Retrieves a paginated list of route bus stops based on the status.
     *
     * @param status   Boolean value indicating the status of the route bus stops to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of RouteBusStopModel objects associated with the specified status.
     */
    Page<RouteBusStopModel> findByStatus(Boolean status, Pageable pageable);
}
