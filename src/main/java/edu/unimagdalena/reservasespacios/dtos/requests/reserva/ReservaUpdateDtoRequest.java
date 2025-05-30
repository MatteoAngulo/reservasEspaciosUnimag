package edu.unimagdalena.reservasespacios.dtos.requests.reserva;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReservaUpdateDtoRequest(
        @NotNull(message = "El idHorarioEspacio no puede ser null") Long idHorarioEspacio,
        @Future(message = "La fecha debe ser futura") LocalDate fecha,
        @NotNull @Size(min = 5, max = 250) String motivo
) {
}
