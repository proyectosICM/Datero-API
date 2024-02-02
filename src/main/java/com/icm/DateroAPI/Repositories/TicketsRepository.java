package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.TicketsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to TicketsModel entities.
 * This repository provides methods for retrieving ticket records from the system.
 */
@Repository
public interface TicketsRepository extends JpaRepository<TicketsModel, Long> {

    /**
     * Retrieves a list of tickets based on the company ID and route ID.
     *
     * @param companyId The ID of the company to retrieve tickets for.
     * @param routeId   The ID of the route to retrieve tickets for.
     * @return List of TicketsModel objects associated with the specified company and route.
     */
    List<TicketsModel> findByCompaniesModelIdAndRoutesModelId(Long companyId, Long routeId);

    /**
     * Retrieves a paginated list of tickets based on the company ID and route ID.
     *
     * @param companyId The ID of the company to retrieve tickets for.
     * @param routeId   The ID of the route to retrieve tickets for.
     * @param pageable  Pageable object for pagination.
     * @return Page of TicketsModel objects associated with the specified company and route.
     */
    Page<TicketsModel> findByCompaniesModelIdAndRoutesModelId(Long companyId, Long routeId, Pageable pageable);

}
