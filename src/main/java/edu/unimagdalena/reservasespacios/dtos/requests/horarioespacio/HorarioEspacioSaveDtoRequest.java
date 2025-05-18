package edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio;

import java.time.LocalDate;

public record HorarioEspacioSaveDtoRequest(
        Long idHorario,
        Long idEspacio,
        LocalDate fecha
) {
}
