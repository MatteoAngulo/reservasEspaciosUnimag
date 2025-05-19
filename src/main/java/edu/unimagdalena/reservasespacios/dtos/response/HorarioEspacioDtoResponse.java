package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.enums.EstadoEspacio;

import java.time.LocalDate;


public record HorarioEspacioDtoResponse(
        Long idHorarioEspacio,
        Long idHorario,
        Long idEspacio,
        EstadoEspacio estadoEspacio,
        LocalDate fecha
) {
}
