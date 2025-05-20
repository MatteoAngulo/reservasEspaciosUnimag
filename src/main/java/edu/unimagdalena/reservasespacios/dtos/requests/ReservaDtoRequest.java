package edu.unimagdalena.reservasespacios.dtos.requests;

import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReservaDtoRequest(
        Long idEstudiante,
        Long idHorarioEspacio,
        @Future LocalDate fecha,
        @Size(min = 5, max = 1000) String motivo
) {
}
