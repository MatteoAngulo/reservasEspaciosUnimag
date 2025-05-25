package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaCambioEstadoDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaUpdateDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.services.interfaces.ReservaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/admin/reservas")
@RestController
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping()
    public ResponseEntity<List<ReservaDtoResponse>> obtenerTodasReservas(){
        return ResponseEntity.ok(reservaService.findAllReservas());
    }

    @GetMapping("/por-id/{idReserva}")
    public ResponseEntity<ReservaDtoResponse> obtenerReservaPorId(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idReserva){

        return ResponseEntity.ok(reservaService.findReservaById(idReserva));
    }

    @GetMapping("/por-estudiante/{idEst}")
    public ResponseEntity<List<ReservaDtoResponse>> obtenerReservasPorEstudiante(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idEst){

        return ResponseEntity.ok(reservaService.findReservasPorEstudiante(idEst));
    }

    @GetMapping("/por-estado")
    public ResponseEntity<List<ReservaDtoResponse>> obtenerReservasPorEstado(@RequestParam
                                                                            EstadoReserva estado){

        return ResponseEntity.ok(reservaService.findReservasPorEstado(estado));
    }

    @GetMapping("/por-horario-y-fecha")
    public ResponseEntity<ReservaDtoResponse> obtenerReservaPorHorarioEspacioYFecha(
            @RequestParam LocalDate fecha,
            @RequestParam Long idHorarioEspacio
    ){

        return ResponseEntity.ok(reservaService.findReservaPorHorarioEspacioYFecha(fecha, idHorarioEspacio));
    }

    @PostMapping
    public ResponseEntity<ReservaDtoResponse> guardarReserva(@RequestBody @Valid ReservaDtoRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.saveReservaAdmin(dto));
    }

    @PutMapping("/{idReserva}")
    public ResponseEntity<ReservaDtoResponse> actualizarReserva(
            @PathVariable
            @NotNull(message = "El id no puede ser nulo")
            @Positive(message = "El id debe ser positivo")
            Long idReserva,
            @RequestBody @Valid ReservaUpdateDtoRequest dto){

        return ResponseEntity.ok(reservaService.updateReservaAdmin(idReserva, dto));
    }

    @PatchMapping("/{idReserva}/cancelar")
    public ResponseEntity<ReservaDtoResponse> cancelarReserva(
            @PathVariable Long idReserva,
            @RequestBody @Valid ReservaCambioEstadoDtoRequest dto){

        return ResponseEntity.ok(reservaService.cancelarReserva(dto, idReserva));
    }

    @PatchMapping("/{idReserva}/aprobar")
    public ResponseEntity<ReservaDtoResponse> aprobarReserva(@PathVariable Long idReserva){
        return ResponseEntity.ok(reservaService.aprobarReserva(idReserva));
    }

    @PatchMapping("/{idReserva}/rechazar")
    public ResponseEntity<ReservaDtoResponse> rechazarReserva(
            @PathVariable Long idReserva,
            @RequestBody @Valid ReservaCambioEstadoDtoRequest dto){

        return ResponseEntity.ok(reservaService.rechazarReserva(idReserva, dto));
    }

}
