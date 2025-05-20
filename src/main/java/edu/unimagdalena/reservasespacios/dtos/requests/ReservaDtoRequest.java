package edu.unimagdalena.reservasespacios.dtos.requests;

import edu.unimagdalena.reservasespacios.enums.EstadoReserva;

public record ReservaDtoRequest(
        Long idEstudiante,
        Long idHorarioEspacio,
        String motivo
) {
}
