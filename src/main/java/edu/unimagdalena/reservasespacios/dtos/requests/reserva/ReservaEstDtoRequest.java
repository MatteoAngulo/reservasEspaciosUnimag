package edu.unimagdalena.reservasespacios.dtos.requests.reserva;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReservaEstDtoRequest(
        Long idHorarioEspacio,
        @Future LocalDate fecha,
        @Size(min = 5) String motivo
) {
}
