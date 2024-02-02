package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.DistrictsModel;
import com.icm.DateroAPI.Models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {
    /**
     * Retrieves a list of districts based on the status.
     *
     * @param status Boolean value indicating the status of the districts to retrieve.
     * @return List of DistrictsModel objects associated with the specified status.
     */
    List<RolesModel> findByStatus(Boolean status);

}
