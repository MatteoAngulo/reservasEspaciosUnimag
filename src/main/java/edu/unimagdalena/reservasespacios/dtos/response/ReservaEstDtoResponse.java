package edu.unimagdalena.reservasespacios.dtos.response;

import java.time.LocalDate;

public record ReservaEstDtoResponse(
        Long idReserva,
        Long idHorarioEspacio,
        LocalDate fecha,
        String motivo
) {
}
