package edu.unimagdalena.reservasespacios.dtos.requests.reserva;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservaCambioEstadoDtoRequest(
        @NotNull Long idReserva,
        @NotNull @NotBlank String motivo
) {
}
