package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.TicketsModel;
import com.icm.DateroAPI.Repositories.TicketsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to TicketsModel entities.
 * This service provides methods for retrieving, creating, updating, and deleting ticket records in the system.
 */
@Service
public class TicketsService {

    @Autowired
    private TicketsRepository ticketsRepository;

    /**
     * Retrieves a paginated list of all tickets in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of TicketsModel objects.
     */
    public Page<TicketsModel> getAll(Pageable pageable) {
        return ticketsRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific ticket by its ID.
     *
     * @param id The ID of the ticket to retrieve.
     * @return Optional containing the TicketsModel if found, otherwise empty.
     */
    public Optional<TicketsModel> getById(Long id) {
        return ticketsRepository.findById(id);
    }

    /**
     * Retrieves a list of tickets based on the company ID and route ID.
     *
     * @param companyId The ID of the company to retrieve tickets for.
     * @param routeId   The ID of the route to retrieve tickets for.
     * @return List of TicketsModel objects associated with the specified company and route.
     */
    public List<TicketsModel> getTicketsByCompanyAndRoute(Long companyId, Long routeId) {
        return ticketsRepository.findByCompaniesModelIdAndRoutesModelId(companyId, routeId);
    }

    /**
     * Retrieves a paginated list of tickets based on the company ID and route ID.
     *
     * @param companyId The ID of the company to retrieve tickets for.
     * @param routeId   The ID of the route to retrieve tickets for.
     * @param pageable  Pageable object for pagination.
     * @return Page of TicketsModel objects associated with the specified company and route.
     */
    public Page<TicketsModel> getTicketsByCompanyAndRoutePaginated(Long companyId, Long routeId, Pageable pageable) {
        return ticketsRepository.findByCompaniesModelIdAndRoutesModelId(companyId, routeId, pageable);
    }

    /**
     * Creates a new ticket record in the system.
     *
     * @param ticketsModel The TicketsModel object representing the new ticket.
     * @return The created TicketsModel.
     */
    public TicketsModel createTicket(TicketsModel ticketsModel) {
        return ticketsRepository.save(ticketsModel);
    }

    /**
     * Updates an existing ticket record in the system.
     *
     * @param id            ID of the ticket to update.
     * @param updatedTicket Updated TicketsModel object with the modified fields.
     * @return The updated TicketsModel.
     */
    public TicketsModel updateTicket(Long id, TicketsModel updatedTicket) {
        TicketsModel existingTicket = ticketsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));

        // Update the fields you allow to change
        existingTicket.setName(updatedTicket.getName());
        existingTicket.setValue(updatedTicket.getValue());
        existingTicket.setRoutesModel(updatedTicket.getRoutesModel());
        existingTicket.setCompaniesModel(updatedTicket.getCompaniesModel());

        // Save the updated ticket to the database
        return ticketsRepository.save(existingTicket);
    }

    /**
     * Deletes a ticket record from the system based on its ID.
     *
     * @param id The ID of the ticket to be deleted.
     */
    public void deleteTicket(Long id) {
        ticketsRepository.deleteById(id);
    }
}
