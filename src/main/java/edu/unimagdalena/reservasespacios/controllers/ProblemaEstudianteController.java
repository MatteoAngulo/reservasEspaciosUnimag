package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.ProblemaService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/estudiantes/{idEst}/problemas")
public class ProblemaEstudianteController {

    private final ProblemaService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<ProblemaDtoResponse> getById(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idEst,
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long id) {
        return ResponseEntity.ok(service.findProblemaById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<List<ProblemaDtoResponse>> getAll(@PathVariable
                                                                @NotNull(message = "El id no puede ser nulo")
                                                                @Positive(message = "El id debe ser positivo")
                                                                Long idEst) {
        return ResponseEntity.ok(service.findProblemasPorEstudiante(idEst));
    }

    @GetMapping("/estado")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<List<ProblemaDtoResponse>> getByEstado(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idEst,
            @RequestParam String estado) {
        // Filtrar para que solo aparezcan los del estudiante en sesión
        List<ProblemaDtoResponse> lista = service.findProblemasPorEstado(estado)
                .stream()
                .filter(p -> p.idEstudiante().equals(idEst)) // aquí ajustar según response tenga estudianteId
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/espacio/{idEsp}")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<List<ProblemaDtoResponse>> getByEspacio(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idEst,
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idEsp) {
        List<ProblemaDtoResponse> lista = service.findProblemasPorEspacio(idEsp)
                .stream()
                .filter(p -> p.idEstudiante().equals(idEst)) // ajustar estudianteId
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<ProblemaDtoResponse> create(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idEst,
            @RequestBody @Valid ProblemaDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveProblema(idEst, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<Void> delete(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idEst,
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long id) {
        service.deleteProblemaEstudiante(idEst, id);
        return ResponseEntity.noContent().build();
    }
}