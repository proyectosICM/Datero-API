package com.icm.DateroAPI.Controllers;

import com.icm.DateroAPI.Models.RouteRegistrationModel;
import com.icm.DateroAPI.Services.RouteRegistrationService;
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

@RestController
@RequestMapping("api/route-registrations")
public class RouteRegistrationController {

    @Autowired
    private RouteRegistrationService routeRegistrationService;

    /**
     * Endpoint para obtener una lista paginada de todos los registros de ruta.
     *
     * @param pageable Objeto Pageable para paginación.
     * @return Página de objetos RouteRegistrationModel.
     */
    @GetMapping("/page")
    public Page<RouteRegistrationModel> getAllRouteRegistrationsPaginated(Pageable pageable) {
        return routeRegistrationService.getAll(pageable);
    }

    /**
     * Endpoint para obtener un registro de ruta específico por su ID.
     *
     * @param id ID del registro de ruta a recuperar.
     * @return ResponseEntity que contiene el RouteRegistrationModel si se encuentra, de lo contrario, vacío.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RouteRegistrationModel> getRouteRegistrationById(@PathVariable Long id) {
        Optional<RouteRegistrationModel> routeRegistration = routeRegistrationService.getById(id);
        return routeRegistration.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * Endpoint para crear un nuevo registro de ruta en el sistema.
     *
     * @param routeRegistrationModel Objeto RouteRegistrationModel que representa el nuevo registro de ruta.
     * @return RouteRegistrationModel creado.
     */
    @PostMapping
    public ResponseEntity<RouteRegistrationModel> createRouteRegistration(@Valid @RequestBody RouteRegistrationModel routeRegistrationModel) {
        RouteRegistrationModel createdRouteRegistration = routeRegistrationService.createRouteRegistration(routeRegistrationModel);
        return new ResponseEntity<>(createdRouteRegistration, HttpStatus.CREATED);
    }

    /**
     * Endpoint para actualizar un registro de ruta existente en el sistema.
     *
     * @param id                      ID del registro de ruta a actualizar.
     * @param updatedRouteRegistration Objeto RouteRegistrationModel actualizado con los campos modificados.
     * @return RouteRegistrationModel actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RouteRegistrationModel> updateRouteRegistration(@PathVariable Long id, @Valid @RequestBody RouteRegistrationModel updatedRouteRegistration) {
        try {
            RouteRegistrationModel updated = routeRegistrationService.updateRouteRegistration(id, updatedRouteRegistration);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para eliminar un registro de ruta del sistema según su ID.
     *
     * @param id ID del registro de ruta a eliminar.
     * @return ResponseEntity sin contenido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRouteRegistration(@PathVariable Long id) {
        routeRegistrationService.deleteRouteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
