package edu.unimagdalena.reservasespacios.dtos.requests;

import edu.unimagdalena.reservasespacios.entities.EstadoReserva;

public record ReservaDtoRequest(
        Long idEstudiante,
        Long idHorarioEspacio,
        String motivo
) {
}
