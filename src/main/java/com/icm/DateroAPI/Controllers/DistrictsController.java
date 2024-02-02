package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.DistrictsModel;
import com.icm.DateroAPI.Services.DistrictsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing operations related to Districts entities.
 * This controller provides RESTful endpoints for retrieving, creating, updating, and deleting district records in the system.
 */
@RestController
@RequestMapping("api/districts")
public class DistrictsController {

    @Autowired
    private DistrictsService districtsService;

    /**
     * Retrieves a paginated list of all districts in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of DistrictsModel objects.
     */
    @GetMapping("/page")
    public Page<DistrictsModel> getAllDistrictsPaginated(Pageable pageable) {
        return districtsService.getAll(pageable);
    }

    /**
     * Retrieves a specific district by its ID.
     *
     * @param id The ID of the district to retrieve.
     * @return ResponseEntity containing the DistrictsModel if found (HTTP OK), otherwise ResponseEntity.notFound().
     */
    @GetMapping("/{id}")
    public ResponseEntity<DistrictsModel> getDistrictById(@PathVariable Long id) {
        Optional<DistrictsModel> district = districtsService.getById(id);
        return district.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of districts based on their status.
     *
     * @param status Boolean value indicating the status of the districts to retrieve.
     * @return List of DistrictsModel objects associated with the specified status.
     */
    @GetMapping("/status/{status}")
    public List<DistrictsModel> getDistrictsByStatus(@PathVariable Boolean status) {
        return districtsService.getDistrictsByStatus(status);
    }

    /**
     * Retrieves a paginated list of districts based on their status.
     *
     * @param status   Boolean value indicating the status of the districts to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of DistrictsModel objects associated with the specified status.
     */
    @GetMapping("/status/{status}/page")
    public Page<DistrictsModel> getDistrictsByStatusPaginated(@PathVariable Boolean status, Pageable pageable) {
        return districtsService.getDistrictsByStatusPaginated(status, pageable);
    }

    /**
     * Creates a new district record in the system.
     *
     * @param districtsModel The DistrictsModel object representing the new district.
     * @return ResponseEntity containing the created DistrictsModel (HTTP CREATED).
     */
    @PostMapping
    public ResponseEntity<DistrictsModel> createDistrict(@Valid @RequestBody DistrictsModel districtsModel) {
        DistrictsModel createdDistrict = districtsService.createDistrict(districtsModel);
        return new ResponseEntity<>(createdDistrict, HttpStatus.CREATED);
    }

    /**
     * Updates an existing district record in the system.
     *
     * @param id              ID of the district to update.
     * @param updatedDistrict Updated DistrictsModel object with the modified fields.
     * @return ResponseEntity containing the updated DistrictsModel (HTTP OK),
     * otherwise ResponseEntity.notFound() if the district is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DistrictsModel> updateDistrict(@PathVariable Long id, @Valid @RequestBody DistrictsModel updatedDistrict) {
        try {
            DistrictsModel updated = districtsService.updateDistrict(id, updatedDistrict);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a district record from the system based on its ID.
     *
     * @param id The ID of the district to be deleted.
     * @return ResponseEntity with no content (HTTP NO_CONTENT).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Long id) {
        districtsService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }
}
