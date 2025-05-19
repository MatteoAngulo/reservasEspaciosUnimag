package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.enums.EstadoEspacio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


public record HorarioEspacioDtoResponse(
        Long idHorarioEspacio,
        Long idHorario,
        Long idEspacio,
        EstadoEspacio estadoEspacio,
        List<DayOfWeek> listDias
) {
}
