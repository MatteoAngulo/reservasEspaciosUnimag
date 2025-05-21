package edu.unimagdalena.reservasespacios.dtos.requests.reserva;

import java.time.LocalDate;

public record ReservaUpdateDtoRequest(
        Long idHorarioEspacio,
        LocalDate fecha,
        String motivo
) {
}
