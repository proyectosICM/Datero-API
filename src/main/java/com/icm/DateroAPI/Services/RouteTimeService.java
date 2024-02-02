package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.RouteTimeModel;
import com.icm.DateroAPI.Repositories.RouteTimeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing operations related to RouteTimeModel entities.
 * This service provides methods for retrieving, creating, updating, and deleting route time records in the system.
 */
@Service
public class RouteTimeService {

    @Autowired
    private RouteTimeRepository routeTimeRepository;

    /**
     * Retrieves a paginated list of all route times in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of RouteTimeModel objects.
     */
    public Page<RouteTimeModel> getAll(Pageable pageable) {
        return routeTimeRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific route time by its ID.
     *
     * @param id The ID of the route time to retrieve.
     * @return Optional containing the RouteTimeModel if found, otherwise empty.
     */
    public Optional<RouteTimeModel> getById(Long id) {
        return routeTimeRepository.findById(id);
    }

    /**
     * Creates a new route time record in the system.
     *
     * @param routeTimeModel The RouteTimeModel object representing the new route time.
     * @return The created RouteTimeModel.
     */
    public RouteTimeModel createRouteTime(RouteTimeModel routeTimeModel) {
        return routeTimeRepository.save(routeTimeModel);
    }

    /**
     * Updates an existing route time record in the system.
     *
     * @param id              ID of the route time to update.
     * @param updatedRouteTime Updated RouteTimeModel object with the modified fields.
     * @return The updated RouteTimeModel.
     */
    public RouteTimeModel updateRouteTime(Long id, RouteTimeModel updatedRouteTime) {
        RouteTimeModel existingRouteTime = routeTimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Route time not found with id: " + id));

        // Update the fields you allow to change
        existingRouteTime.setExpectedPass(updatedRouteTime.getExpectedPass());
        existingRouteTime.setActualPassTime(updatedRouteTime.getActualPassTime());
        existingRouteTime.setRoutesModel(updatedRouteTime.getRoutesModel());
        existingRouteTime.setBusesModel(updatedRouteTime.getBusesModel());
        existingRouteTime.setBusStopsModel(updatedRouteTime.getBusStopsModel());

        // Save the updated route time to the database
        return routeTimeRepository.save(existingRouteTime);
    }

    /**
     * Deletes a route time record from the system based on its ID.
     *
     * @param id The ID of the route time to be deleted.
     */
    public void deleteRouteTime(Long id) {
        routeTimeRepository.deleteById(id);
    }
}
