package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.entities.EstadoReserva;

public record ReservaDtoResponse(
        Long idReserva,
        Long idEstudiante,
        Long idHorarioEspacio,
        EstadoReserva estadoReserva,
        String motivo
) {
}
