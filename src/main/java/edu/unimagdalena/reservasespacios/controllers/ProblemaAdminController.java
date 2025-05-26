package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.ProblemaService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/problemas")
public class ProblemaAdminController {

    private final ProblemaService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<ProblemaDtoResponse>> getAll() {
        return ResponseEntity.ok(service.findAllProblemas());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProblemaDtoResponse> getById(@PathVariable
                                                           @NotNull(message = "El id no puede ser nulo")
                                                           @Positive(message = "El id debe ser positivo")
                                                           Long id) {
        return ResponseEntity.ok(service.findProblemaById(id));
    }

    @GetMapping("/estudiante/{idEst}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<ProblemaDtoResponse>> getByEstudiante(@PathVariable
                                                                         @NotNull(message = "El id no puede ser nulo")
                                                                         @Positive(message = "El id debe ser positivo")
                                                                         Long idEst) {
        return ResponseEntity.ok(service.findProblemasPorEstudiante(idEst));
    }

    @GetMapping("/estado")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<ProblemaDtoResponse>> getByEstado(@RequestParam String estado) {
        return ResponseEntity.ok(service.findProblemasPorEstado(estado));
    }

    @GetMapping("/espacio/{idEsp}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<ProblemaDtoResponse>> getByEspacio(@PathVariable
                                                                      @NotNull(message = "El id no puede ser nulo")
                                                                      @Positive(message = "El id debe ser positivo")
                                                                      Long idEsp) {
        return ResponseEntity.ok(service.findProblemasPorEspacio(idEsp));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProblemaDtoResponse> update(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long id,
            @RequestBody @Valid ProblemaDtoRequest dto) {
        return ResponseEntity.ok(service.updateProblema(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> delete(@PathVariable
                                           @NotNull(message = "El id no puede ser nulo")
                                           @Positive(message = "El id debe ser positivo")
                                           Long id) {
        service.deleteProblema(id);
        return ResponseEntity.noContent().build();
    }
}