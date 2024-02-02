package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.BusesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to Buses entities.
 * This repository provides methods for retrieving bus records from the system.
 */
@Repository
public interface BusesRepository extends JpaRepository<BusesModel, Long> {
    /**
     * Retrieves a list of buses based on the associated company ID.
     *
     * @param companyId ID of the company associated with the buses.
     * @return List of BusesModel objects associated with the specified company ID.
     */
    List<BusesModel> findByCompaniesModelId(Long companyId);

    /**
     * Retrieves a paginated list of buses based on the associated company ID.
     *
     * @param companyId ID of the company associated with the buses.
     * @param pageable  Pageable object for pagination.
     * @return Page of BusesModel objects associated with the specified company ID.
     */
    Page<BusesModel> findByCompaniesModelId(Long companyId, Pageable pageable);

    /**
     * Retrieves a list of buses based on the associated company ID and status.
     *
     * @param companyId ID of the company associated with the buses.
     * @param status    Boolean value indicating the status of the buses to retrieve.
     * @return List of BusesModel objects associated with the specified company ID and status.
     */
    List<BusesModel> findByCompaniesModelIdAndStatus(Long companyId, Boolean status);


    /**
     * Retrieves a paginated list of buses based on the associated company ID and status.
     *
     * @param companyId ID of the company associated with the buses.
     * @param status    Boolean value indicating the status of the buses to retrieve.
     * @param pageable  Pageable object for pagination.
     * @return Page of BusesModel objects associated with the specified company ID and status.
     */
    Page<BusesModel> findByCompaniesModelIdAndStatus(Long companyId, Boolean status, Pageable pageable);
}