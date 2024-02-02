package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.CompaniesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to Companies entities.
 * This repository provides methods for retrieving company records from the system.
 */
@Repository
public interface CompaniesRepository extends JpaRepository<CompaniesModel, Long> {
    /**
     * Retrieves a list of companies based on the status.
     *
     * @param status Boolean value indicating the status of the companies to retrieve.
     * @return List of CompaniesModel objects associated with the specified status.
     */
    List<CompaniesModel> findByStatus(Boolean status);

    /**
     * Retrieves a paginated list of companies based on the status.
     *
     * @param status   Boolean value indicating the status of the companies to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of CompaniesModel objects associated with the specified status.
     */
    Page<CompaniesModel> findByStatus(Boolean status, Pageable pageable);
}
