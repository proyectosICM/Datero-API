package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.RouteBusStopModel;
import com.icm.DateroAPI.Repositories.RouteBusStopRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to RouteBusStopModel entities.
 * This service provides methods for retrieving, creating, updating, and deleting route bus stop records in the system.
 */
@Service
public class RouteBusStopService {

    @Autowired
    private RouteBusStopRepository routeBusStopRepository;

    /**
     * Retrieves a paginated list of all route bus stops in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of RouteBusStopModel objects.
     */
    public Page<RouteBusStopModel> getAll(Pageable pageable) {
        return routeBusStopRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific route bus stop by its ID.
     *
     * @param id The ID of the route bus stop to retrieve.
     * @return Optional containing the RouteBusStopModel if found, otherwise empty.
     */
    public Optional<RouteBusStopModel> getById(Long id) {
        return routeBusStopRepository.findById(id);
    }

    /**
     * Retrieves a list of route bus stops based on their status.
     *
     * @param status Boolean value indicating the status of the route bus stops to retrieve.
     * @return List of RouteBusStopModel objects associated with the specified status.
     */
    public List<RouteBusStopModel> getRouteBusStopsByStatus(Boolean status) {
        return routeBusStopRepository.findByStatus(status);
    }

    /**
     * Retrieves a paginated list of route bus stops based on their status.
     *
     * @param status   Boolean value indicating the status of the route bus stops to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of RouteBusStopModel objects associated with the specified status.
     */
    public Page<RouteBusStopModel> getRouteBusStopsByStatusPaginated(Boolean status, Pageable pageable) {
        return routeBusStopRepository.findByStatus(status, pageable);
    }

    /**
     * Creates a new route bus stop record in the system.
     *
     * @param routeBusStopModel The RouteBusStopModel object representing the new route bus stop.
     * @return The created RouteBusStopModel.
     */
    public RouteBusStopModel createRouteBusStop(RouteBusStopModel routeBusStopModel) {
        return routeBusStopRepository.save(routeBusStopModel);
    }

    /**
     * Updates an existing route bus stop record in the system.
     *
     * @param id                ID of the route bus stop to update.
     * @param updatedRouteBusStop Updated RouteBusStopModel object with the modified fields.
     * @return The updated RouteBusStopModel.
     */
    public RouteBusStopModel updateRouteBusStop(Long id, RouteBusStopModel updatedRouteBusStop) {
        RouteBusStopModel existingRouteBusStop = routeBusStopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Route bus stop not found with id: " + id));

        // Update the fields you allow to change
        existingRouteBusStop.setOrder(updatedRouteBusStop.getOrder());
        existingRouteBusStop.setStatus(updatedRouteBusStop.getStatus());
        existingRouteBusStop.setRoutesModel(updatedRouteBusStop.getRoutesModel());
        existingRouteBusStop.setBusStopsModel(updatedRouteBusStop.getBusStopsModel());

        // Save the updated route bus stop to the database
        return routeBusStopRepository.save(existingRouteBusStop);
    }

    /**
     * Deletes a route bus stop record from the system based on its ID.
     *
     * @param id The ID of the route bus stop to be deleted.
     */
    public void deleteRouteBusStop(Long id) {
        routeBusStopRepository.deleteById(id);
    }
}
