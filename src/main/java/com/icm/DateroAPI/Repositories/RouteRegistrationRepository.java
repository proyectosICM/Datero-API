package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.RouteRegistrationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing operations related to RouteRegistrationModel entities.
 * This repository provides methods for retrieving, creating, updating, and deleting route registration records in the system.
 */
@Repository
public interface RouteRegistrationRepository extends JpaRepository<RouteRegistrationModel, Long> {

}
