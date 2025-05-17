package edu.unimagdalena.reservasespacios.dtos.response;

import java.util.Date;

public record HorarioDtoResponse(
        Long idHorario,
        Date horaInicio,
        Date horaFin
) {
}
