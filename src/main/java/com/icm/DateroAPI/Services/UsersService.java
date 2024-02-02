package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.UsersModel;
import com.icm.DateroAPI.Repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to UsersModel entities.
 * This service provides methods for retrieving, creating, updating, and deleting user records in the system.
 */
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Retrieves a paginated list of all users in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of UsersModel objects.
     */
    public Page<UsersModel> getAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific user by its ID.
     *
     * @param id The ID of the user to retrieve.
     * @return Optional containing the UsersModel if found, otherwise empty.
     */
    public Optional<UsersModel> getById(Long id) {
        return usersRepository.findById(id);
    }

    /**
     * Retrieves a list of users based on the company ID.
     *
     * @param companiesId The ID of the company to retrieve users for.
     * @return List of UsersModel objects associated with the specified company.
     */
    public List<UsersModel> getUsersByCompany(Long companiesId) {
        return usersRepository.findByCompaniesModelId(companiesId);
    }

    /**
     * Retrieves a list of users based on the company ID and status.
     *
     * @param companiesId The ID of the company to retrieve users for.
     * @param status      Boolean value indicating the status of the users to retrieve.
     * @return List of UsersModel objects associated with the specified company and status.
     */
    public List<UsersModel> getUsersByCompanyAndStatus(Long companiesId, Boolean status) {
        return usersRepository.findByCompaniesModelIdAndStatus(companiesId, status);
    }

    /**
     * Creates a new user record in the system.
     *
     * @param usersModel The UsersModel object representing the new user.
     * @return The created UsersModel.
     */
    public UsersModel createUser(UsersModel usersModel) {
        return usersRepository.save(usersModel);
    }

    /**
     * Updates an existing user record in the system.
     *
     * @param id            ID of the user to update.
     * @param updatedUser Updated UsersModel object with the modified fields.
     * @return The updated UsersModel.
     */
    public UsersModel updateUser(Long id, UsersModel updatedUser) {
        UsersModel existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Update the fields you allow to change
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setDni(updatedUser.getDni());
        existingUser.setStatus(updatedUser.getStatus());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRolesModel(updatedUser.getRolesModel());
        existingUser.setCompaniesModel(updatedUser.getCompaniesModel());

        // Save the updated user to the database
        return usersRepository.save(existingUser);
    }

    /**
     * Deletes a user record from the system based on its ID.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
