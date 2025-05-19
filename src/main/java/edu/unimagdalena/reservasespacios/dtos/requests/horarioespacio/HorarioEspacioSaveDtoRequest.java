package edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record HorarioEspacioSaveDtoRequest(
        Long idHorario,
        Long idEspacio,
        List<DayOfWeek> listDias,
        LocalTime horaInicio,
        LocalTime horaFin
) {
}
