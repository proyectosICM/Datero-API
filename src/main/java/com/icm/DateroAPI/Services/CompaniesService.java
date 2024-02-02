package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.CompaniesModel;
import com.icm.DateroAPI.Repositories.CompaniesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to Companies entities.
 * This service provides methods for retrieving, creating, updating, and deleting company records in the system.
 */
@Service
public class CompaniesService {
    @Autowired
    private CompaniesRepository companiesRepository;

    /**
     * Retrieves a paginated list of all companies in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of CompaniesModel objects.
     */
    public Page<CompaniesModel> getAll(Pageable pageable) {
        return companiesRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return Optional containing the CompaniesModel if found, otherwise empty.
     */
    public Optional<CompaniesModel> getById(Long id) {
        return companiesRepository.findById(id);
    }

    /**
     * Retrieves a list of companies based on their status.
     *
     * @param status Boolean value indicating the status of the companies to retrieve.
     * @return List of CompaniesModel objects associated with the specified status.
     */
    public List<CompaniesModel> getCompaniesByStatus(Boolean status) {
        return companiesRepository.findByStatus(status);
    }

    /**
     * Retrieves a paginated list of companies based on their status.
     *
     * @param status    Boolean value indicating the status of the companies to retrieve.
     * @param pageable  Pageable object for pagination.
     * @return Page of CompaniesModel objects associated with the specified status.
     */
    public Page<CompaniesModel> getCompaniesByStatusPaginated(Boolean status, Pageable pageable) {
        return companiesRepository.findByStatus(status, pageable);
    }

    /**
     * Creates a new company record in the system.
     *
     * @param companiesModel The CompaniesModel object representing the new company.
     * @return The created CompaniesModel.
     */
    public CompaniesModel createCompany(CompaniesModel companiesModel) {
        return companiesRepository.save(companiesModel);
    }

    /**
     * Updates an existing company record in the system.
     *
     * @param id             ID of the company to update.
     * @param updatedCompany Updated CompaniesModel object with the modified fields.
     * @return The updated CompaniesModel.
     */
    public CompaniesModel updateCompany(Long id, CompaniesModel updatedCompany) {
        CompaniesModel existingCompany = companiesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + id));

        // Update the fields you allow to change
        existingCompany.setName(updatedCompany.getName());
        existingCompany.setStatus(updatedCompany.getStatus());

        // Save the updated company to the database
        return companiesRepository.save(existingCompany);
    }

    /**
     * Deletes a company record from the system based on its ID.
     *
     * @param id The ID of the company to be deleted.
     */
    public void deleteCompany(Long id) {
        companiesRepository.deleteById(id);
    }
}
