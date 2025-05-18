package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.entities.EstadoEspacio;

import java.time.LocalDate;


public record HorarioEspacioDtoResponse(
        Long idHorarioEspacio,
        Long idHorario,
        Long idEspacio,
        EstadoEspacio estadoEspacio,
        LocalDate fecha
) {
}
