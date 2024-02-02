package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.BusesModel;
import com.icm.DateroAPI.Services.BusesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class to manage operations related to Buses entities.
 * This service provides methods to retrieve, create, update, and delete bus records in the system.
 */
@RestController
@RequestMapping("api/buses")
public class BusesController {
    @Autowired
    private BusesService busesService;

    /**
     * Retrieves a paginated list of all buses in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of BusesModel objects.
     */
    @GetMapping("/page")
    public Page<BusesModel> getAllBusesPaginated(Pageable pageable) {
        return busesService.getAll(pageable);
    }

    /**
     * Retrieves a specific bus by its ID.
     *
     * @param id The ID of the bus to retrieve.
     * @return ResponseEntity containing the BusesModel if found, otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BusesModel> getById(@PathVariable Long id) {
        Optional<BusesModel> company = busesService.getById(id);
        return company.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of buses based on the associated company ID.
     *
     * @param companyId ID of the company associated with the buses.
     * @return List of BusesModel objects associated with the specified company ID.
     */
    @GetMapping("/company/{companyId}")
    public List<BusesModel> getBusesByCompanyId(@PathVariable Long companyId) {
        return busesService.findByCompaniesModelId(companyId);
    }

    /**
     * Retrieves a paginated list of buses based on the associated company ID.
     *
     * @param companyId ID of the company associated with the buses.
     * @param pageable  Pageable object for pagination.
     * @return Page of BusesModel objects associated with the specified company ID.
     */
    @GetMapping("/company/{companyId}/page")
    public Page<BusesModel> getBusesByCompanyIdPaginated(@PathVariable Long companyId, Pageable pageable) {
        return busesService.findByCompaniesModelId(companyId, pageable);
    }

    /**
     * Retrieves a list of buses based on the associated company ID and status.
     *
     * @param companyId ID of the company associated with the buses.
     * @param status    Boolean value indicating the status of the buses to retrieve.
     * @return List of BusesModel objects associated with the specified company ID and status.
     */
    @GetMapping("/company/{companyId}/status/{status}")
    public List<BusesModel> getBusesByCompanyIdAndStatus(@PathVariable Long companyId, @PathVariable Boolean status) {
        return busesService.findByCompaniesModelIdAndStatus(companyId, status);
    }

    /**
     * Retrieves a paginated list of buses based on the associated company ID and status.
     *
     * @param companyId ID of the company associated with the buses.
     * @param status    Boolean value indicating the status of the buses to retrieve.
     * @param pageable  Pageable object for pagination.
     * @return Page of BusesModel objects associated with the specified company ID and status.
     */
    @GetMapping("/company/{companyId}/status/{status}/page")
    public Page<BusesModel> getBusesByCompanyIdAndStatusPaginated(
            @PathVariable Long companyId, @PathVariable Boolean status, Pageable pageable) {
        return busesService.findByCompaniesModelIdAndStatus(companyId, status, pageable);
    }


    /**
     * Creates a new bus record in the system.
     *
     * @param busesModel The BusesModel object representing the new bus.
     * @return ResponseEntity containing the created BusesModel and HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<BusesModel> createBus(@RequestBody BusesModel busesModel) {
        BusesModel createdBus = busesService.createBus(busesModel);
        return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
    }

    /**
     * Updates an existing bus in the database.
     *
     * @param id         ID of the bus to update.
     * @param updatedBus Updated BusesModel object with the modified fields.
     * @return ResponseEntity containing the updated BusesModel if successful,
     * otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BusesModel> updateBus(@PathVariable Long id, @RequestBody BusesModel updatedBus) {
        try {
            BusesModel updated = busesService.updateBus(id, updatedBus);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a bus record from the system based on its ID.
     *
     * @param id The ID of the bus to be deleted.
     * @return ResponseEntity with HttpStatus.NO_CONTENT if deletion is successful,
     * otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        try {
            busesService.deleteBus(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
