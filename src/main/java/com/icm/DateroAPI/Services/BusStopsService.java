package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.BusStopsModel;
import com.icm.DateroAPI.Models.BusesModel;
import com.icm.DateroAPI.Repositories.BusStopsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusStopsService {
    @Autowired
    private BusStopsRepository busStopsRepository;

    /**
     * Retrieves a paginated list of all bus stops in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of BusStopsModel objects.
     */
    public Page<BusStopsModel> getAll(Pageable pageable) {
        return busStopsRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific bus stop by its ID.
     *
     * @param id The ID of the bus stop to retrieve.
     * @return Optional containing the BusStopsModel if found, otherwise empty.
     */
    public Optional<BusStopsModel> getById(Long id) {
        return busStopsRepository.findById(id);
    }

    /**
     * Retrieves a list of bus stops based on their status.
     *
     * @param status Boolean value indicating the status of the bus stops to retrieve.
     * @return List of BusStopsModel objects associated with the specified status.
     */
    public List<BusStopsModel> getByStatus(Boolean status) {
        return busStopsRepository.findByStatus(status);
    }

    /**
     * Retrieves a paginated list of bus stops based on their status.
     *
     * @param status   Boolean value indicating the status of the bus stops to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of BusStopsModel objects associated with the specified status.
     */
    public Page<BusStopsModel> getByStatus(Boolean status, Pageable pageable) {
        return busStopsRepository.findByStatus(status, pageable);
    }

    /**
     * Creates a new bus stop record in the system.
     *
     * @param busStopsModel The BusStopsModel object representing the new bus stop.
     * @return The created BusStopsModel.
     */
    public BusStopsModel createBusStop(BusStopsModel busStopsModel) {
        return busStopsRepository.save(busStopsModel);
    }

    /**
     * Updates an existing bus stop record in the system.
     *
     * @param id             ID of the bus stop to update.
     * @param updatedBusStop Updated BusStopsModel object with the modified fields.
     * @return The updated BusStopsModel.
     */
    public BusStopsModel updateBusStop(Long id, BusStopsModel updatedBusStop) {
        BusStopsModel existingBusStop = busStopsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus stop not found with id: " + id));

        // Update the fields you allow to change
        existingBusStop.setName(updatedBusStop.getName());
        existingBusStop.setStatus(updatedBusStop.getStatus());
        existingBusStop.setLongitude(updatedBusStop.getLongitude());
        existingBusStop.setLatitude(updatedBusStop.getLatitude());
        existingBusStop.setDistrictsModel(updatedBusStop.getDistrictsModel());

        // Save the updated bus stop to the database
        return busStopsRepository.save(existingBusStop);
    }

    /**
     * Deletes a bus stop record from the system based on its ID.
     *
     * @param id The ID of the bus stop to be deleted.
     */
    public void deleteBusStop(Long id) {
        busStopsRepository.deleteById(id);
    }

}
