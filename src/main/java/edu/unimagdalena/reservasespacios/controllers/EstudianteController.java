package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOUpdate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.EstudianteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping("/")
    //@PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<EstudianteDTOResponse> registrarEstudiante(@RequestBody @Valid EstudianteDTOCreate estudianteDTOCreate) {
        return new ResponseEntity<>(estudianteService.saveEstudiante(estudianteDTOCreate), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EstudianteDTOResponse> findEstudianteById(@PathVariable
                                                                    @NotNull(message = "El id no puede estar vacio")
                                                                    @Positive(message = "El id debe ser positivo")
                                                                    Long id) {
        return ResponseEntity.ok(estudianteService.findEstudianteById(id));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EstudianteDTOResponse> findEstudianteByCodigo(@RequestParam
                                                                        @NotNull(message = "El codigo no puede estar vacio")
                                                                        @Positive(message = "El codigo no puede ser negativo")
                                                                        Long codigo) {
        return ResponseEntity.ok(estudianteService.findEstudianteByCodigoEstudiantil(codigo));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<EstudianteDTOResponse>> findAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.findEstudiantes());
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EstudianteDTOResponse> actualizarEstudiante(@RequestBody @Valid EstudianteDTOUpdate estudianteDTOUpdate) {
        return ResponseEntity.ok(estudianteService.updateEstudiante(estudianteDTOUpdate));
    }

    @DeleteMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> borrarEstudiante(@RequestParam
                                                 @NotNull(message = "El codigo no puede estar vacio")
                                                 @Positive(message = "El codigo debe ser positivo")
                                                 Long codigo) {
        estudianteService.deleteEstudianteByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

}
