package edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio;

import edu.unimagdalena.reservasespacios.entities.EstadoEspacio;

import java.time.LocalDate;

public record HorarioEspacioDtoRequest(
        Long idHorario,
        Long idEspacio,
        EstadoEspacio estadoEspacio,
        LocalDate fecha
) {
}
