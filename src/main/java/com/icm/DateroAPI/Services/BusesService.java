package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.BusesModel;
import com.icm.DateroAPI.Repositories.BusesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusesService {
    @Autowired
    private BusesRepository busesRepository;

    /**
     * Retrieves a paginated list of all buses in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of BusesModel objects.
     */
    public Page<BusesModel> getAll(Pageable pageable) {
        return busesRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific bus by its ID.
     *
     * @param id The ID of the bus to retrieve.
     * @return Optional containing the BusesModel if found, otherwise empty.
     */
    public Optional<BusesModel> getById(Long id) {
        return busesRepository.findById(id);
    }

    /**
     * Retrieves a list of buses based on the associated company ID.
     *
     * @param companyId ID of the company associated with the buses.
     * @return List of BusesModel objects associated with the specified company ID.
     */
    public List<BusesModel> findByCompaniesModelId(Long companyId) {
        return busesRepository.findByCompaniesModelId(companyId);
    }

    /**
     * Retrieves a paginated list of buses based on the associated company ID.
     *
     * @param companyId ID of the company associated with the buses.
     * @param pageable  Pageable object for pagination.
     * @return Page of BusesModel objects associated with the specified company ID.
     */
    public Page<BusesModel> findByCompaniesModelId(Long companyId, Pageable pageable) {
        return busesRepository.findByCompaniesModelId(companyId, pageable);
    }

    /**
     * Retrieves a list of buses based on the associated company ID and status.
     *
     * @param companyId ID of the company associated with the buses.
     * @param status    Boolean value indicating the status of the buses to retrieve.
     * @return List of BusesModel objects associated with the specified company ID and status.
     */
    public List<BusesModel> findByCompaniesModelIdAndStatus(Long companyId, Boolean status) {
        return busesRepository.findByCompaniesModelIdAndStatus(companyId, status);
    }

    /**
     * Retrieves a paginated list of buses based on the associated company ID and status.
     *
     * @param companyId ID of the company associated with the buses.
     * @param status    Boolean value indicating the status of the buses to retrieve.
     * @param pageable  Pageable object for pagination.
     * @return Page of BusesModel objects associated with the specified company ID and status.
     */
    public Page<BusesModel> findByCompaniesModelIdAndStatus(Long companyId, Boolean status, Pageable pageable) {
        return busesRepository.findByCompaniesModelIdAndStatus(companyId, status, pageable);
    }

    /**
     * Creates a new bus record in the system.
     *
     * @param busesModel The BusesModel object representing the new bus.
     * @return The created BusesModel.
     */
    public BusesModel createBus(BusesModel busesModel) {
        return busesRepository.save(busesModel);
    }

    /**
     * Updates an existing bus in the database.
     *
     * @param id         ID of the bus to update.
     * @param updatedBus Updated BusesModel object with the modified fields.
     * @return Updated BusesModel object.
     * @throws EntityNotFoundException If the bus with the provided ID is not found.
     */
    public BusesModel updateBus(Long id, BusesModel updatedBus) {
        BusesModel existingBus = busesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus not found with id: " + id));

        // Update the fields you allow to change
        existingBus.setModel(updatedBus.getModel());
        existingBus.setPlate(updatedBus.getPlate());
        existingBus.setLongitude(updatedBus.getLongitude());
        existingBus.setLatitude(updatedBus.getLatitude());
        existingBus.setStatus(updatedBus.getStatus());

        // If you need to change other fields, add setters here

        // Save the updated bus to the database
        return busesRepository.save(existingBus);
    }


    /**
     * Deletes a bus record from the system based on its ID.
     *
     * @param id The ID of the bus to be deleted.
     */
    public void deleteBus(Long id) {
        busesRepository.deleteById(id);
    }
}
