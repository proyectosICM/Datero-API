package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.BusStopsModel;
import com.icm.DateroAPI.Services.BusStopsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bus-stops")
public class BusStopsController {
    @Autowired
    private BusStopsService busStopsService;

    /**
     * Retrieves a paginated list of all bus stops in the system.
     *
     * @param pageable Pageable object for pagination.
     * @return Page of BusStopsModel objects.
     */
    @GetMapping("/page")
    public ResponseEntity<Page<BusStopsModel>> getAllBusStopsPaginated(@RequestParam Pageable pageable) {
        Page<BusStopsModel> busStops = busStopsService.getAll(pageable);
        return ResponseEntity.ok(busStops);
    }

    /**
     * Retrieves a specific bus stop by its ID.
     *
     * @param id The ID of the bus stop to retrieve.
     * @return ResponseEntity containing the BusStopsModel if found,
     * otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BusStopsModel> getBusStopById(@PathVariable Long id) {
        return busStopsService.getById(id)
                .map(busStop -> new ResponseEntity<>(busStop, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of bus stops based on their status.
     *
     * @param status Boolean value indicating the status of the bus stops to retrieve.
     * @return ResponseEntity containing List of BusStopsModel objects associated with the specified status.
     */
    @GetMapping("/status")
    public ResponseEntity<List<BusStopsModel>> getBusStopsByStatus(@RequestParam Boolean status) {
        List<BusStopsModel> busStops = busStopsService.getByStatus(status);
        return ResponseEntity.ok(busStops);
    }

    /**
     * Retrieves a paginated list of bus stops based on their status.
     *
     * @param status   Boolean value indicating the status of the bus stops to retrieve.
     * @param pageable Pageable object for pagination.
     * @return ResponseEntity containing Page of BusStopsModel objects associated with the specified status.
     */
    @GetMapping("/status/page")
    public ResponseEntity<Page<BusStopsModel>> getBusStopsByStatusPaginated(@RequestParam Boolean status, Pageable pageable) {
        Page<BusStopsModel> busStops = busStopsService.getByStatus(status, pageable);
        return ResponseEntity.ok(busStops);
    }


    /**
     * Creates a new bus stop record in the system.
     *
     * @param busStopsModel The BusStopsModel object representing the new bus stop.
     * @return ResponseEntity containing the created BusStopsModel and HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<BusStopsModel> createBusStop(@RequestBody BusStopsModel busStopsModel) {
        BusStopsModel createdBusStop = busStopsService.createBusStop(busStopsModel);
        return new ResponseEntity<>(createdBusStop, HttpStatus.CREATED);
    }

    /**
     * Updates an existing bus stop record in the system.
     *
     * @param id             ID of the bus stop to update.
     * @param updatedBusStop Updated BusStopsModel object with the modified fields.
     * @return ResponseEntity containing the updated BusStopsModel if successful,
     * otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BusStopsModel> updateBusStop(@PathVariable Long id, @RequestBody BusStopsModel updatedBusStop) {
        try {
            BusStopsModel updated = busStopsService.updateBusStop(id, updatedBusStop);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a bus stop record from the system based on its ID.
     *
     * @param id The ID of the bus stop to be deleted.
     * @return ResponseEntity with HttpStatus.NO_CONTENT if deletion is successful,
     * otherwise returns ResponseEntity with HttpStatus.NOT_FOUND.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusStop(@PathVariable Long id) {
        try {
            busStopsService.deleteBusStop(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
