package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.RoutesModel;
import com.icm.DateroAPI.Repositories.RoutesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to RoutesModel entities.
 * This service provides methods for retrieving, creating, updating, and deleting route records in the system.
 */
@Service
public class RoutesService {

    @Autowired
    private RoutesRepository routesRepository;

    /**
     * Retrieves a paginated list of all routes in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of RoutesModel objects.
     */
    public Page<RoutesModel> getAll(Pageable pageable) {
        return routesRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific route by its ID.
     *
     * @param id The ID of the route to retrieve.
     * @return Optional containing the RoutesModel if found, otherwise empty.
     */
    public Optional<RoutesModel> getById(Long id) {
        return routesRepository.findById(id);
    }

    /**
     * Retrieves a list of routes based on the company ID.
     *
     * @param companyId The ID of the company to retrieve routes for.
     * @return List of RoutesModel objects associated with the specified company.
     */
    public List<RoutesModel> getRoutesByCompany(Long companyId) {
        return routesRepository.findByCompaniesModelId(companyId);
    }

    /**
     * Retrieves a paginated list of routes based on the company ID.
     *
     * @param companyId The ID of the company to retrieve routes for.
     * @param pageable  Pageable object for pagination.
     * @return Page of RoutesModel objects associated with the specified company.
     */
    public Page<RoutesModel> getRoutesByCompanyPaginated(Long companyId, Pageable pageable) {
        return routesRepository.findByCompaniesModelId(companyId, pageable);
    }

    /**
     * Retrieves a list of routes based on the company ID and status.
     *
     * @param companyId The ID of the company to retrieve routes for.
     * @param status    Boolean value indicating the status of the routes to retrieve.
     * @return List of RoutesModel objects associated with the specified company and status.
     */
    public List<RoutesModel> getRoutesByCompanyAndStatus(Long companyId, Boolean status) {
        return routesRepository.findByCompaniesModelIdAndStatus(companyId, status);
    }

    /**
     * Retrieves a paginated list of routes based on the company ID and status.
     *
     * @param companyId The ID of the company to retrieve routes for.
     * @param status    Boolean value indicating the status of the routes to retrieve.
     * @param pageable  Pageable object for pagination.
     * @return Page of RoutesModel objects associated with the specified company and status.
     */
    public Page<RoutesModel> getRoutesByCompanyAndStatusPaginated(Long companyId, Boolean status, Pageable pageable) {
        return routesRepository.findByCompaniesModelIdAndStatus(companyId, status, pageable);
    }

    /**
     * Creates a new route record in the system.
     *
     * @param routeModel The RoutesModel object representing the new route.
     * @return The created RoutesModel.
     */
    public RoutesModel createRoute(RoutesModel routeModel) {
        return routesRepository.save(routeModel);
    }

    /**
     * Updates an existing route record in the system.
     *
     * @param id            ID of the route to update.
     * @param updatedRoute Updated RoutesModel object with the modified fields.
     * @return The updated RoutesModel.
     */
    public RoutesModel updateRoute(Long id, RoutesModel updatedRoute) {
        RoutesModel existingRoute = routesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Route not found with id: " + id));

        // Update the fields you allow to change
        existingRoute.setName(updatedRoute.getName());
        existingRoute.setStatus(updatedRoute.getStatus());
        existingRoute.setCompaniesModel(updatedRoute.getCompaniesModel());

        // Save the updated route to the database
        return routesRepository.save(existingRoute);
    }

    /**
     * Deletes a route record from the system based on its ID.
     *
     * @param id The ID of the route to be deleted.
     */
    public void deleteRoute(Long id) {
        routesRepository.deleteById(id);
    }
}
