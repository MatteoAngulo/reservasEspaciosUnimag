package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.ProblemaService;
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
@RequestMapping("/api/problemas")
public class ProblemaController {

    private final ProblemaService problemaService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<ProblemaDtoResponse> crearProblema(@RequestBody @Valid ProblemaDtoRequest dto){
        ProblemaDtoResponse created = problemaService.saveProblema(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<List<ProblemaDtoResponse>> listarProblemas(){
        List<ProblemaDtoResponse> list = problemaService.findAllProblemas();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<ProblemaDtoResponse> obtenerProblema(@PathVariable
                                                                   @NotNull(message = "El id no puede estar vacio")
                                                                   @Positive(message = "El id debe ser positivo")
                                                                   Long id){
        ProblemaDtoResponse resp = problemaService.findProblemaById(id);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProblemaDtoResponse> actualizarProblema(@PathVariable
                                                                      @NotNull(message = "El id no puede estar vacio")
                                                                      @Positive(message = "El id debe ser positivo")
                                                                      Long id,
            @RequestBody @Valid ProblemaDtoRequest dto){
        ProblemaDtoResponse updated = problemaService.updateProblema(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> eliminarProblema(@PathVariable
                                                     @NotNull(message = "El id no puede estar vacio")
                                                     @Positive(message = "El id debe ser positivo")
                                                     Long id){
        problemaService.deleteProblema(id);
        return ResponseEntity.noContent().build();
    }
}
