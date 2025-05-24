package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.services.interfaces.ReservaService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    //Admin
    @GetMapping()
    public ResponseEntity<List<ReservaDtoResponse>> obtenerTodasReservas(){
        return ResponseEntity.ok(reservaService.findAllReservas());
    }

    //Ambos
    @GetMapping("{idReserva}")
    public ResponseEntity<ReservaDtoResponse> obtenerReservaPorId(@PathVariable
                                                                      @NotNull(message = "El id no puede ser nulo")
                                                                      @Positive(message = "El id debe ser positivo")
                                                                      Long idReserva){

        return ResponseEntity.ok(reservaService.findReservaById(idReserva));
    }

    //Ambos
    @GetMapping("/por-estudiante/{idEst}")
    public ResponseEntity<List<ReservaDtoResponse>> obtenerReservaPorEstudiante(@PathVariable
                                                                                    @NotNull(message = "El id no puede ser nulo")
                                                                                    @Positive(message = "El id debe ser positivo")
                                                                                    Long idEst){

        return ResponseEntity.ok(reservaService.findReservasPorEstudiante(idEst));
    }

    //Admin
    @GetMapping("/por-estado")
    public ResponseEntity<List<ReservaDtoResponse>> obtenerReservaPorEstado(@RequestParam
                                                                            EstadoReserva estado){

        return ResponseEntity.ok(reservaService.findReservasPorEstado(estado));
    }

    //Admin
    @GetMapping("/por-horario-y-fecha")
    public ResponseEntity<ReservaDtoResponse> obtenerReservaPorHorarioEspacioYFecha(
            @RequestParam LocalDate fecha,
            @RequestParam Long idHorarioEspacio
            ){

        return ResponseEntity.ok(reservaService.findReservaPorHorarioEspacioYFecha(fecha, idHorarioEspacio));
    }



}
