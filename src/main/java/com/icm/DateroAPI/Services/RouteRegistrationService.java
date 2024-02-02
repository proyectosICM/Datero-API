package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.RouteRegistrationModel;
import com.icm.DateroAPI.Repositories.RouteRegistrationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to RouteRegistrationModel entities.
 * This service provides methods for retrieving, creating, updating, and deleting route registration records in the system.
 */
@Service
public class RouteRegistrationService {

    @Autowired
    private RouteRegistrationRepository routeRegistrationRepository;

    /**
     * Retrieves a paginated list of all route registrations in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of RouteRegistrationModel objects.
     */
    public Page<RouteRegistrationModel> getAll(Pageable pageable) {
        return routeRegistrationRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific route registration by its ID.
     *
     * @param id The ID of the route registration to retrieve.
     * @return Optional containing the RouteRegistrationModel if found, otherwise empty.
     */
    public Optional<RouteRegistrationModel> getById(Long id) {
        return routeRegistrationRepository.findById(id);
    }


    /**
     * Creates a new route registration record in the system.
     *
     * @param routeRegistrationModel The RouteRegistrationModel object representing the new route registration.
     * @return The created RouteRegistrationModel.
     */
    public RouteRegistrationModel createRouteRegistration(RouteRegistrationModel routeRegistrationModel) {
        return routeRegistrationRepository.save(routeRegistrationModel);
    }

    /**
     * Updates an existing route registration record in the system.
     *
     * @param id                      ID of the route registration to update.
     * @param updatedRouteRegistration Updated RouteRegistrationModel object with the modified fields.
     * @return The updated RouteRegistrationModel.
     */
    public RouteRegistrationModel updateRouteRegistration(Long id, RouteRegistrationModel updatedRouteRegistration) {
        RouteRegistrationModel existingRouteRegistration = routeRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Route registration not found with id: " + id));

        // Update the fields you allow to change
        existingRouteRegistration.setDay(updatedRouteRegistration.getDay());
        existingRouteRegistration.setExpectedArrivalTime(updatedRouteRegistration.getExpectedArrivalTime());
        existingRouteRegistration.setActualArrivalTime(updatedRouteRegistration.getActualArrivalTime());
        existingRouteRegistration.setCompaniesModel(updatedRouteRegistration.getCompaniesModel());
        existingRouteRegistration.setRoutesModel(updatedRouteRegistration.getRoutesModel());
        existingRouteRegistration.setBusesModel(updatedRouteRegistration.getBusesModel());
        existingRouteRegistration.setBusStopsModel(updatedRouteRegistration.getBusStopsModel());

        // Save the updated route registration to the database
        return routeRegistrationRepository.save(existingRouteRegistration);
    }

    /**
     * Deletes a route registration record from the system based on its ID.
     *
     * @param id The ID of the route registration to be deleted.
     */
    public void deleteRouteRegistration(Long id) {
        routeRegistrationRepository.deleteById(id);
    }
}
