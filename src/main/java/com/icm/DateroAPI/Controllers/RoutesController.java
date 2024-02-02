package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.RoutesModel;
import com.icm.DateroAPI.Services.RoutesService;
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
 * Controller class to manage operations related to Routes entities.
 * This service provides methods to retrieve, create, update, and delete route records in the system.
 */
@RestController
@RequestMapping("api/routes")
public class RoutesController {

    @Autowired
    private RoutesService routesService;

    @GetMapping("/page")
    public Page<RoutesModel> getAllRoutesPaginated(Pageable pageable) {
        return routesService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutesModel> getRouteById(@PathVariable Long id) {
        Optional<RoutesModel> route = routesService.getById(id);
        return route.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/company/{companyId}")
    public List<RoutesModel> getRoutesByCompany(@PathVariable Long companyId) {
        return routesService.getRoutesByCompany(companyId);
    }

    @GetMapping("/company/{companyId}/page")
    public Page<RoutesModel> getRoutesByCompanyPaginated(@PathVariable Long companyId, Pageable pageable) {
        return routesService.getRoutesByCompanyPaginated(companyId, pageable);
    }

    @GetMapping("/company/{companyId}/status/{status}")
    public List<RoutesModel> getRoutesByCompanyAndStatus(@PathVariable Long companyId, @PathVariable Boolean status) {
        return routesService.getRoutesByCompanyAndStatus(companyId, status);
    }

    @GetMapping("/company/{companyId}/status/{status}/page")
    public Page<RoutesModel> getRoutesByCompanyAndStatusPaginated(@PathVariable Long companyId, @PathVariable Boolean status, Pageable pageable) {
        return routesService.getRoutesByCompanyAndStatusPaginated(companyId, status, pageable);
    }

    @PostMapping
    public ResponseEntity<RoutesModel> createRoute(@Valid @RequestBody RoutesModel routeModel) {
        RoutesModel createdRoute = routesService.createRoute(routeModel);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutesModel> updateRoute(@PathVariable Long id, @Valid @RequestBody RoutesModel updatedRoute) {
        try {
            RoutesModel updated = routesService.updateRoute(id, updatedRoute);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routesService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
