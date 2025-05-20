package edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio;

import edu.unimagdalena.reservasespacios.enums.EstadoEspacio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record HorarioEspacioDtoRequest(
        Long idHorario,
        Long idEspacio,
        EstadoEspacio estadoEspacio,
        List<DayOfWeek> listDias,
        LocalTime horaInicio,
        LocalTime horaFin
) {
}
