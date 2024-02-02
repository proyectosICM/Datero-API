package com.icm.DateroAPI.Services;

import com.icm.DateroAPI.Models.TicketCountModel;
import com.icm.DateroAPI.Repositories.TicketCountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing operations related to TicketCountModel entities.
 * This service provides methods for retrieving, creating, updating, and deleting ticket count records in the system.
 */
@Service
public class TicketCountService {

    @Autowired
    private TicketCountRepository ticketCountRepository;

    /**
     * Retrieves a paginated list of all ticket counts in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of TicketCountModel objects.
     */
    public Page<TicketCountModel> getAll(Pageable pageable) {
        return ticketCountRepository.findAll(pageable);
    }

    /**
     * Retrieves a specific ticket count by its ID.
     *
     * @param id The ID of the ticket count to retrieve.
     * @return Optional containing the TicketCountModel if found, otherwise empty.
     */
    public Optional<TicketCountModel> getById(Long id) {
        return ticketCountRepository.findById(id);
    }

    /**
     * Creates a new ticket count record in the system.
     *
     * @param ticketCountModel The TicketCountModel object representing the new ticket count.
     * @return The created TicketCountModel.
     */
    public TicketCountModel createTicketCount(TicketCountModel ticketCountModel) {
        return ticketCountRepository.save(ticketCountModel);
    }

    /**
     * Updates an existing ticket count record in the system.
     *
     * @param id               ID of the ticket count to update.
     * @param updatedTicketCount Updated TicketCountModel object with the modified fields.
     * @return The updated TicketCountModel.
     */
    public TicketCountModel updateTicketCount(Long id, TicketCountModel updatedTicketCount) {
        TicketCountModel existingTicketCount = ticketCountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket count not found with id: " + id));

        // Update the fields you allow to change
        existingTicketCount.setCount(updatedTicketCount.getCount());
        existingTicketCount.setDay(updatedTicketCount.getDay());
        existingTicketCount.setTotalAccumulated(updatedTicketCount.getTotalAccumulated());
        existingTicketCount.setTicketsModel(updatedTicketCount.getTicketsModel());
        existingTicketCount.setBusesModel(updatedTicketCount.getBusesModel());
        existingTicketCount.setCompaniesModel(updatedTicketCount.getCompaniesModel());

        // Save the updated ticket count to the database
        return ticketCountRepository.save(existingTicketCount);
    }

    /**
     * Deletes a ticket count record from the system based on its ID.
     *
     * @param id The ID of the ticket count to be deleted.
     */
    public void deleteTicketCount(Long id) {
        ticketCountRepository.deleteById(id);
    }
}
