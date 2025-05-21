package edu.unimagdalena.reservasespacios.dtos.requests;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record HorarioEspacioDtoRequest(
        Long idEspacio,
        DayOfWeek dia,
        LocalTime horaInicio,
        LocalTime horaFin
) {
}
