package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.SedeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    private final SedeService sedeService;


    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<SedeDtoResponse> createSede(@RequestBody @Valid SedeDtoRequest dto) {
        SedeDtoResponse created = sedeService.saveSede(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<List<SedeDtoResponse>> getAllSedes() {
        List<SedeDtoResponse> list = sedeService.findAllSedes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<SedeDtoResponse> getSedeById(@PathVariable
                                                           @NotNull(message = "El id no puede estar vacio")
                                                           @Positive(message = "El id debe ser positivo") Long id) {
        SedeDtoResponse sede = sedeService.findSedeById(id);
        return ResponseEntity.ok(sede);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<SedeDtoResponse> updateSede(
            @PathVariable
            @NotNull(message = "El id no puede estar vacio")
            @Positive(message = "El id debe ser positivo")
            Long id,
            @RequestBody @Valid SedeDtoRequest dto) {
        SedeDtoResponse updated = sedeService.updateSede(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteSede(@PathVariable
                                               @NotNull(message = "El id no puede estar vacio")
                                               @Positive(message = "El id debe ser positivo") Long id) {
        sedeService.deleteSede(id);
        return ResponseEntity.noContent().build();
    }
}
