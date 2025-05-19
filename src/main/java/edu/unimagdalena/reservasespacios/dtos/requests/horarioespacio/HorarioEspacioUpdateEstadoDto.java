package edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio;

import edu.unimagdalena.reservasespacios.entities.EstadoEspacio;
import lombok.Builder;

@Builder
public record HorarioEspacioUpdateEstadoDto(
        EstadoEspacio estadoEspacio
) {
}
