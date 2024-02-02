package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.RolesModel;
import com.icm.DateroAPI.Repositories.RolesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to Roles entities.
 * This service provides methods for retrieving, creating, updating, and deleting role records in the system.
 */
@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    /**
     * Retrieves a paginated list of all roles in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of RolesModel objects.
     */
    public Page<RolesModel> getAll(Pageable pageable) {
        return rolesRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific role by its ID.
     *
     * @param id The ID of the role to retrieve.
     * @return Optional containing the RolesModel if found, otherwise empty.
     */
    public Optional<RolesModel> getById(Long id) {
        return rolesRepository.findById(id);
    }

    /**
     * Retrieves a list of roles based on their status.
     *
     * @param status Boolean value indicating the status of the roles to retrieve.
     * @return List of RolesModel objects associated with the specified status.
     */
    public List<RolesModel> getRolesByStatus(Boolean status) {
        return rolesRepository.findByStatus(status);
    }

    /**
     * Creates a new role record in the system.
     *
     * @param rolesModel The RolesModel object representing the new role.
     * @return The created RolesModel.
     */
    public RolesModel createRole(RolesModel rolesModel) {
        return rolesRepository.save(rolesModel);
    }

    /**
     * Updates an existing role record in the system.
     *
     * @param id           ID of the role to update.
     * @param updatedRole Updated RolesModel object with the modified fields.
     * @return The updated RolesModel.
     */
    public RolesModel updateRole(Long id, RolesModel updatedRole) {
        RolesModel existingRole = rolesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));

        // Update the fields you allow to change
        existingRole.setName(updatedRole.getName());
        existingRole.setStatus(updatedRole.getStatus());

        // Save the updated role to the database
        return rolesRepository.save(existingRole);
    }

    /**
     * Deletes a role record from the system based on its ID.
     *
     * @param id The ID of the role to be deleted.
     */
    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }
}
