package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.DistrictsModel;
import com.icm.DateroAPI.Repositories.DistrictsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to Districts entities.
 * This service provides methods for retrieving, creating, updating, and deleting district records in the system.
 */
@Service
public class DistrictsService {

    @Autowired
    private DistrictsRepository districtsRepository;

    /**
     * Retrieves a paginated list of all districts in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of DistrictsModel objects.
     */
    public Page<DistrictsModel> getAll(Pageable pageable) {
        return districtsRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific district by its ID.
     *
     * @param id The ID of the district to retrieve.
     * @return Optional containing the DistrictsModel if found, otherwise empty.
     */
    public Optional<DistrictsModel> getById(Long id) {
        return districtsRepository.findById(id);
    }

    /**
     * Retrieves a list of districts based on their status.
     *
     * @param status Boolean value indicating the status of the districts to retrieve.
     * @return List of DistrictsModel objects associated with the specified status.
     */
    public List<DistrictsModel> getDistrictsByStatus(Boolean status) {
        return districtsRepository.findByStatus(status);
    }

    /**
     * Retrieves a paginated list of districts based on their status.
     *
     * @param status   Boolean value indicating the status of the districts to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of DistrictsModel objects associated with the specified status.
     */
    public Page<DistrictsModel> getDistrictsByStatusPaginated(Boolean status, Pageable pageable) {
        return districtsRepository.findByStatus(status, pageable);
    }

    /**
     * Creates a new district record in the system.
     *
     * @param districtsModel The DistrictsModel object representing the new district.
     * @return The created DistrictsModel.
     */
    public DistrictsModel createDistrict(DistrictsModel districtsModel) {
        return districtsRepository.save(districtsModel);
    }

    /**
     * Updates an existing district record in the system.
     *
     * @param id              ID of the district to update.
     * @param updatedDistrict Updated DistrictsModel object with the modified fields.
     * @return The updated DistrictsModel.
     */
    public DistrictsModel updateDistrict(Long id, DistrictsModel updatedDistrict) {
        DistrictsModel existingDistrict = districtsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("District not found with id: " + id));

        // Update the fields you allow to change
        existingDistrict.setName(updatedDistrict.getName());
        existingDistrict.setStatus(updatedDistrict.getStatus());

        // Save the updated district to the database
        return districtsRepository.save(existingDistrict);
    }

    /**
     * Deletes a district record from the system based on its ID.
     *
     * @param id The ID of the district to be deleted.
     */
    public void deleteDistrict(Long id) {
        districtsRepository.deleteById(id);
    }
}
