package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.RoutesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to RoutesModel entities.
 * This repository provides methods for retrieving route records from the system.
 */
@Repository
public interface RoutesRepository extends JpaRepository<RoutesModel, Long> {

    /**
     * Retrieves a list of routes based on the company ID.
     *
     * @param id The ID of the company to retrieve routes for.
     * @return List of RoutesModel objects associated with the specified company.
     */
    List<RoutesModel> findByCompaniesModelId(Long id);

    /**
     * Retrieves a paginated list of routes based on the company ID.
     *
     * @param id The ID of the company to retrieve routes for.
     * @param pageable Pageable object for pagination.
     * @return Page of RoutesModel objects associated with the specified company.
     */
    Page<RoutesModel> findByCompaniesModelId(Long id, Pageable pageable);

    /**
     * Retrieves a list of routes based on the company ID and status.
     *
     * @param id The ID of the company to retrieve routes for.
     * @param status Boolean value indicating the status of the routes to retrieve.
     * @return List of RoutesModel objects associated with the specified company and status.
     */
    List<RoutesModel> findByCompaniesModelIdAndStatus(Long id, Boolean status);


    /**
     * Retrieves a paginated list of routes based on the company ID and status.
     *
     * @param id The ID of the company to retrieve routes for.
     * @param status Boolean value indicating the status of the routes to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of RoutesModel objects associated with the specified company and status.
     */
    Page<RoutesModel> findByCompaniesModelIdAndStatus(Long id, Boolean status, Pageable pageable);
}
