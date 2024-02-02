package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.CompaniesModel;
import com.icm.DateroAPI.Services.CompaniesService;
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
 * Controller class to manage operations related to Companies entities.
 * This service provides methods to retrieve, create, update, and delete company records in the system.
 */
@RestController
@RequestMapping("api/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;

    /**
     * Retrieves a paginated list of all companies in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of CompaniesModel objects.
     */
    @GetMapping("/page")
    public Page<CompaniesModel> getAllCompaniesPaginated(Pageable pageable) {
        return companiesService.getAll(pageable);
    }

    /**
     * Retrieves a specific company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return ResponseEntity containing the CompaniesModel if found, otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompaniesModel> getById(@PathVariable Long id) {
        Optional<CompaniesModel> company = companiesService.getById(id);
        return company.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of companies based on their status.
     *
     * @param status Boolean value indicating the status of the companies to retrieve.
     * @return List of CompaniesModel objects associated with the specified status.
     */
    @GetMapping("/status")
    public List<CompaniesModel> getCompaniesByStatus(@RequestParam Boolean status) {
        return companiesService.getCompaniesByStatus(status);
    }

    /**
     * Retrieves a paginated list of companies based on their status.
     *
     * @param status   Boolean value indicating the status of the companies to retrieve.
     * @param pageable Pageable object for pagination.
     * @return Page of CompaniesModel objects associated with the specified status.
     */
    @GetMapping("/status/page")
    public Page<CompaniesModel> getCompaniesByStatusPaginated(@RequestParam Boolean status, Pageable pageable) {
        return companiesService.getCompaniesByStatusPaginated(status, pageable);
    }

    /**
     * Creates a new company record in the system.
     *
     * @param companiesModel The CompaniesModel object representing the new company.
     * @return ResponseEntity containing the created CompaniesModel and HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<CompaniesModel> createCompany(@Valid @RequestBody CompaniesModel companiesModel) {
        CompaniesModel createdCompany = companiesService.createCompany(companiesModel);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    /**
     * Updates an existing company record in the system.
     *
     * @param id             The ID of the company to update.
     * @param updatedCompany Updated CompaniesModel.
     * @return ResponseEntity containing the updated CompaniesModel if successful,
     * otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompaniesModel> updateCompany(@PathVariable Long id, @Valid @RequestBody CompaniesModel updatedCompany) {
        try {
            CompaniesModel updated = companiesService.updateCompany(id, updatedCompany);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a company record from the system based on its ID.
     *
     * @param id The ID of the company to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companiesService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
