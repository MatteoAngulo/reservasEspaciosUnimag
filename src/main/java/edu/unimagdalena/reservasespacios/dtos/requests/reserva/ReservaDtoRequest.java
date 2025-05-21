package edu.unimagdalena.reservasespacios.dtos.requests.reserva;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReservaDtoRequest(
        Long idEstudiante,
        Long idHorarioEspacio,
        @Future LocalDate fecha,
        @NotNull @Size(min = 5, max = 1000) String motivo
) {
}
