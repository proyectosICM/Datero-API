package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.TicketCountModel;
import com.icm.DateroAPI.Services.TicketCountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/ticket-counts")
public class TicketCountController {

    @Autowired
    private TicketCountService ticketCountService;

    @GetMapping("/page")
    public Page<TicketCountModel> getAllTicketCountsPaginated(Pageable pageable) {
        return ticketCountService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketCountModel> getTicketCountById(@PathVariable Long id) {
        Optional<TicketCountModel> ticketCount = ticketCountService.getById(id);
        return ticketCount.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TicketCountModel> createTicketCount(@Valid @RequestBody TicketCountModel ticketCountModel) {
        TicketCountModel createdTicketCount = ticketCountService.createTicketCount(ticketCountModel);
        return new ResponseEntity<>(createdTicketCount, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketCountModel> updateTicketCount(@PathVariable Long id, @Valid @RequestBody TicketCountModel updatedTicketCount) {
        try {
            TicketCountModel updated = ticketCountService.updateTicketCount(id, updatedTicketCount);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketCount(@PathVariable Long id) {
        ticketCountService.deleteTicketCount(id);
        return ResponseEntity.noContent().build();
    }
}
