package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to UsersModel entities.
 * This repository provides methods for retrieving user records from the system.
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {

    /**
     * Retrieves a list of users based on the company ID.
     *
     * @param companiesId The ID of the company to retrieve users for.
     * @return List of UsersModel objects associated with the specified company.
     */
    List<UsersModel> findByCompaniesModelId(Long companiesId);

    /**
     * Retrieves a list of users based on the company ID and status.
     *
     * @param companiesId The ID of the company to retrieve users for.
     * @param status      Boolean value indicating the status of the users to retrieve.
     * @return List of UsersModel objects associated with the specified company and status.
     */
    List<UsersModel> findByCompaniesModelIdAndStatus(Long companiesId, Boolean status);
}
