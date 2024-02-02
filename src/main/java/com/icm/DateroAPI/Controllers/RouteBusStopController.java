package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.RouteBusStopModel;
import com.icm.DateroAPI.Services.RouteBusStopService;
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
 * Controller class for managing endpoints related to RouteBusStopModel entities.
 * This controller provides endpoints for retrieving, creating, updating, and deleting route bus stop records in the system.
 */
@RestController
@RequestMapping("api/route-bus-stops")
public class RouteBusStopController {

    @Autowired
    private RouteBusStopService routeBusStopService;

    @GetMapping("/page")
    public Page<RouteBusStopModel> getAllRouteBusStopsPaginated(Pageable pageable) {
        return routeBusStopService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteBusStopModel> getRouteBusStopById(@PathVariable Long id) {
        Optional<RouteBusStopModel> routeBusStop = routeBusStopService.getById(id);
        return routeBusStop.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<RouteBusStopModel> getRouteBusStopsByStatus(@PathVariable Boolean status) {
        return routeBusStopService.getRouteBusStopsByStatus(status);
    }

    @GetMapping("/status/{status}/page")
    public Page<RouteBusStopModel> getRouteBusStopsByStatusPaginated(@PathVariable Boolean status, Pageable pageable) {
        return routeBusStopService.getRouteBusStopsByStatusPaginated(status, pageable);
    }

    @PostMapping
    public ResponseEntity<RouteBusStopModel> createRouteBusStop(@Valid @RequestBody RouteBusStopModel routeBusStopModel) {
        RouteBusStopModel createdRouteBusStop = routeBusStopService.createRouteBusStop(routeBusStopModel);
        return new ResponseEntity<>(createdRouteBusStop, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteBusStopModel> updateRouteBusStop(@PathVariable Long id, @Valid @RequestBody RouteBusStopModel updatedRouteBusStop) {
        try {
            RouteBusStopModel updated = routeBusStopService.updateRouteBusStop(id, updatedRouteBusStop);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRouteBusStop(@PathVariable Long id) {
        routeBusStopService.deleteRouteBusStop(id);
        return ResponseEntity.noContent().build();
    }
}
