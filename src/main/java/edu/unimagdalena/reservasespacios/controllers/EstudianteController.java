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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping("/registrar")
    public ResponseEntity<EstudianteDTOResponse> registrarEstudiante(@RequestBody @Valid EstudianteDTOCreate estudianteDTOCreate) {
        return new ResponseEntity<>(estudianteService.saveEstudiante(estudianteDTOCreate), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTOResponse> findEstudianteById(@PathVariable
                                                                    @NotNull(message = "El id no puede estar vacio")
                                                                    @Positive(message = "El id debe ser positivo")
                                                                    Long id) {
        return ResponseEntity.ok(estudianteService.findEstudianteById(id));
    }

    @GetMapping("/por-correo")
    public ResponseEntity<EstudianteDTOResponse> findEstudianteByCorreo(@RequestParam
                                                                        @NotBlank(message = "El correo no puede esta vacio")
                                                                        String correo) {
        return ResponseEntity.ok(estudianteService.findEstudianteByCorreo(correo));
    }

    @GetMapping("/por-codigo")
    public ResponseEntity<EstudianteDTOResponse> findEstudianteByCodigo(@RequestParam
                                                                        @NotNull(message = "El codigo no puede estar vacio")
                                                                        @Positive(message = "El codigo no puede ser negativo")
                                                                        String correo) {
        return ResponseEntity.ok(estudianteService.findEstudianteByCorreo(correo));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EstudianteDTOResponse>> findAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.findEstudiantes());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<EstudianteDTOResponse> actualizarEstudiante(@RequestBody @Valid EstudianteDTOUpdate estudianteDTOUpdate) {
        return ResponseEntity.ok(estudianteService.updateEstudiante(estudianteDTOUpdate));
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<Void> borrarEstudiante(@RequestParam @NotBlank(message = "El correo no puede estar vacio")
                                                     String correo ) {
        estudianteService.deleteEstudiante(correo);
        return ResponseEntity.noContent().build();
    }

}
