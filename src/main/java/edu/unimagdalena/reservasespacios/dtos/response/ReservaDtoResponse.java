package edu.unimagdalena.reservasespacios.dtos.response;


import edu.unimagdalena.reservasespacios.enums.EstadoReserva;

import java.time.LocalDate;

public record ReservaDtoResponse(
        Long idReserva,
        Long idEstudiante,
        Long idHorarioEspacio,
        EstadoReserva estadoReserva,
        LocalDate fecha,
        String motivo
) {
}
