package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.TicketsModel;
import com.icm.DateroAPI.Services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for managing RESTful endpoints related to TicketsModel entities.
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketsController {

    @Autowired
    private TicketsService ticketsService;

    /**
     * Retrieves a paginated list of all tickets in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of TicketsModel objects.
     */
    @GetMapping
    public ResponseEntity<Page<TicketsModel>> getAllTickets(Pageable pageable) {
        Page<TicketsModel> tickets = ticketsService.getAll(pageable);
        return ResponseEntity.ok(tickets);
    }

    /**
     * Retrieves a specific ticket by its ID.
     *
     * @param id The ID of the ticket to retrieve.
     * @return ResponseEntity containing the TicketsModel if found, otherwise a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketsModel> getTicketById(@PathVariable Long id) {
        return ticketsService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of tickets based on the company ID and route ID.
     *
     * @param companyId The ID of the company to retrieve tickets for.
     * @param routeId   The ID of the route to retrieve tickets for.
     * @return List of TicketsModel objects associated with the specified company and route.
     */
    @GetMapping("/company/{companyId}/route/{routeId}")
    public ResponseEntity<List<TicketsModel>> getTicketsByCompanyAndRoute(@PathVariable Long companyId, @PathVariable Long routeId) {
        List<TicketsModel> tickets = ticketsService.getTicketsByCompanyAndRoute(companyId, routeId);
        return ResponseEntity.ok(tickets);
    }

    /**
     * Retrieves a paginated list of tickets based on the company ID and route ID.
     *
     * @param companyId The ID of the company to retrieve tickets for.
     * @param routeId   The ID of the route to retrieve tickets for.
     * @param pageable  Pageable object for pagination.
     * @return Page of TicketsModel objects associated with the specified company and route.
     */
    @GetMapping("/company/{companyId}/route/{routeId}/paginated")
    public ResponseEntity<Page<TicketsModel>> getTicketsByCompanyAndRoutePaginated(@PathVariable Long companyId, @PathVariable Long routeId, Pageable pageable) {
        Page<TicketsModel> tickets = ticketsService.getTicketsByCompanyAndRoutePaginated(companyId, routeId, pageable);
        return ResponseEntity.ok(tickets);
    }

    /**
     * Creates a new ticket record in the system.
     *
     * @param ticketsModel The TicketsModel object representing the new ticket.
     * @return ResponseEntity containing the created TicketsModel.
     */
    @PostMapping
    public ResponseEntity<TicketsModel> createTicket(@Valid @RequestBody TicketsModel ticketsModel) {
        TicketsModel createdTicket = ticketsService.createTicket(ticketsModel);
        return ResponseEntity.ok(createdTicket);
    }

    /**
     * Updates an existing ticket record in the system.
     *
     * @param id            ID of the ticket to update.
     * @param updatedTicket Updated TicketsModel object with the modified fields.
     * @return ResponseEntity containing the updated TicketsModel if successful, otherwise a 404 Not Found status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TicketsModel> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketsModel updatedTicket) {
        TicketsModel updated = ticketsService.updateTicket(id, updatedTicket);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a ticket record from the system based on its ID.
     *
     * @param id The ID of the ticket to be deleted.
     * @return ResponseEntity with a 204 No Content status if successful, otherwise a 404 Not Found status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketsService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
