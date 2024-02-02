package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.DistrictsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to Districts entities.
 * This repository provides methods for retrieving district records from the system.
 */
@Repository
public interface DistrictsRepository extends JpaRepository<DistrictsModel, Long> {
    /**
     * Retrieves a list of districts based on the status.
     *
     * @param status Boolean value indicating the status of the districts to retrieve.
     * @return List of DistrictsModel objects associated with the specified status.
     */
    List<DistrictsModel> findByStatus(Boolean status);

    /**
     * Retrieves a paginated list of districts based on the status.
     *
     * @param status   Boolean value indicating the status of the districts to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of DistrictsModel objects associated with the specified status.
     */
    Page<DistrictsModel> findByStatus(Boolean status, Pageable pageable);
}
