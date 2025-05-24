package edu.unimagdalena.reservasespacios.dtos.requests.reserva;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservaAccionesDtoRequest(
        @NotNull Long idReserva,
        @NotNull @NotBlank String motivo
) {
}
