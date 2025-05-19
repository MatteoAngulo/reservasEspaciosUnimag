package edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio;

import edu.unimagdalena.reservasespacios.enums.EstadoEspacio;

public record HorarioEspacioUpdateEstadoDto(
        EstadoEspacio estadoEspacio
) {
}
