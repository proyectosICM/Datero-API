package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.UsersModel;
import com.icm.DateroAPI.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to UsersModel entities.
 */
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * Endpoint to retrieve a paginated list of all users.
     *
     * @param pageable Pageable object for pagination.
     * @return ResponseEntity with a Page of UsersModel objects and HTTP status.
     */
    @GetMapping
    public ResponseEntity<Page<UsersModel>> getAllUsers(Pageable pageable) {
        Page<UsersModel> users = usersService.getAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a specific user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity with an Optional containing the UsersModel if found, otherwise empty, and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsersModel>> getUserById(@PathVariable Long id) {
        Optional<UsersModel> user = usersService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a list of users based on the company ID.
     *
     * @param companiesId The ID of the company to retrieve users for.
     * @return ResponseEntity with a List of UsersModel objects and HTTP status.
     */
    @GetMapping("/company/{companiesId}")
    public ResponseEntity<List<UsersModel>> getUsersByCompany(@PathVariable Long companiesId) {
        List<UsersModel> users = usersService.getUsersByCompany(companiesId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a list of users based on the company ID and status.
     *
     * @param companiesId The ID of the company to retrieve users for.
     * @param status      Boolean value indicating the status of the users to retrieve.
     * @return ResponseEntity with a List of UsersModel objects and HTTP status.
     */
    @GetMapping("/company/{companiesId}/status/{status}")
    public ResponseEntity<List<UsersModel>> getUsersByCompanyAndStatus(@PathVariable Long companiesId,
                                                                       @PathVariable Boolean status) {
        List<UsersModel> users = usersService.getUsersByCompanyAndStatus(companiesId, status);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Endpoint to create a new user record.
     *
     * @param usersModel The UsersModel object representing the new user.
     * @return ResponseEntity with the created UsersModel and HTTP status.
     */
    @PostMapping
    public ResponseEntity<UsersModel> createUser(@RequestBody UsersModel usersModel) {
        UsersModel createdUser = usersService.createUser(usersModel);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing user record.
     *
     * @param id            ID of the user to update.
     * @param updatedUser Updated UsersModel object with the modified fields.
     * @return ResponseEntity with the updated UsersModel and HTTP status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsersModel> updateUser(@PathVariable Long id, @RequestBody UsersModel updatedUser) {
        UsersModel updatedUserData = usersService.updateUser(id, updatedUser);
        return new ResponseEntity<>(updatedUserData, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a user record.
     *
     * @param id The ID of the user to be deleted.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
