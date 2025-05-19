package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.enums.EstadoReserva;

import java.time.LocalDateTime;

public record HistorialReservaDtoResponse(
        Long idHistorial,
        Long idReserva,
        EstadoReserva estadoReserva,
        LocalDateTime fechaCambio,
        String comentario
) {
}
