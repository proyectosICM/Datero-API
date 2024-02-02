package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.RolesModel;
import com.icm.DateroAPI.Services.RolesService;
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
 * Controller class for managing operations related to Roles entities.
 * This controller provides endpoints for retrieving, creating, updating, and deleting role records in the system.
 */
@RestController
@RequestMapping("api/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/page")
    public Page<RolesModel> getAllRolesPaginated(Pageable pageable) {
        return rolesService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesModel> getRoleById(@PathVariable Long id) {
        Optional<RolesModel> role = rolesService.getById(id);
        return role.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<RolesModel> getRolesByStatus(@PathVariable Boolean status) {
        return rolesService.getRolesByStatus(status);
    }

    @PostMapping
    public ResponseEntity<RolesModel> createRole(@Valid @RequestBody RolesModel rolesModel) {
        RolesModel createdRole = rolesService.createRole(rolesModel);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesModel> updateRole(@PathVariable Long id, @Valid @RequestBody RolesModel updatedRole) {
        try {
            RolesModel updated = rolesService.updateRole(id, updatedRole);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        rolesService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
