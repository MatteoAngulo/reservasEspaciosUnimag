package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaCambioEstadoDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaEstDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaEstDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.ReservaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estudiantes/{idEst}/reservas")
public class ReservaEstudianteController {

    private final ReservaService reservaService;

    @GetMapping("/{idReserva}")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<ReservaDtoResponse> obtenerReservaPorId(@PathVariable
                                                                      @NotNull(message = "El id no puede ser nulo")
                                                                      @Positive(message = "El id debe ser positivo")
                                                                      Long idReserva){

        return ResponseEntity.ok(reservaService.findReservaById(idReserva));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<List<ReservaDtoResponse>> obtenerReservasPorEstudiante(@PathVariable Long idEst){
        return ResponseEntity.ok(reservaService.findReservasPorEstudiante(idEst));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<ReservaEstDtoResponse> guardarReserva(
            @PathVariable Long idEst,
            @RequestBody @Valid ReservaEstDtoRequest dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.saveReservaEstudiante(dto, idEst));
    }

    @PutMapping("/{idReserva}")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<ReservaEstDtoResponse> actualizarReserva(
            @PathVariable Long idReserva,
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            @RequestBody @Valid ReservaEstDtoRequest dto
    ){

        return ResponseEntity.ok(reservaService.updateReservaEstudiante(idReserva, dto));
    }

    @PatchMapping("/{idReserva}/cancelar")
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<ReservaDtoResponse> cancelarReserva(
            @PathVariable Long idReserva,
            @RequestBody @Valid ReservaCambioEstadoDtoRequest dto){

        return ResponseEntity.ok(reservaService.cancelarReserva(dto, idReserva));
    }

}
