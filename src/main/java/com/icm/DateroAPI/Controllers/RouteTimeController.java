package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.RouteTimeModel;
import com.icm.DateroAPI.Services.RouteTimeService;
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
@RequestMapping("api/route-times")
public class RouteTimeController {

    @Autowired
    private RouteTimeService routeTimeService;

    @GetMapping("/page")
    public Page<RouteTimeModel> getAllRouteTimesPaginated(Pageable pageable) {
        return routeTimeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteTimeModel> getRouteTimeById(@PathVariable Long id) {
        Optional<RouteTimeModel> routeTime = routeTimeService.getById(id);
        return routeTime.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RouteTimeModel> createRouteTime(@Valid @RequestBody RouteTimeModel routeTimeModel) {
        RouteTimeModel createdRouteTime = routeTimeService.createRouteTime(routeTimeModel);
        return new ResponseEntity<>(createdRouteTime, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteTimeModel> updateRouteTime(@PathVariable Long id, @Valid @RequestBody RouteTimeModel updatedRouteTime) {
        try {
            RouteTimeModel updated = routeTimeService.updateRouteTime(id, updatedRouteTime);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRouteTime(@PathVariable Long id) {
        routeTimeService.deleteRouteTime(id);
        return ResponseEntity.noContent().build();
    }
}
