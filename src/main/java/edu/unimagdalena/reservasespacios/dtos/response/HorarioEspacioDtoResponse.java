package edu.unimagdalena.reservasespacios.dtos.response;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;


public record HorarioEspacioDtoResponse(
        Long idHorarioEspacio,
        Long idEspacio,
        DayOfWeek dia,
        LocalTime horaInicio,
        LocalTime horaFin
) {
}
