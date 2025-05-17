package edu.unimagdalena.reservasespacios.dtos.requests;

import java.time.LocalTime;
import java.util.Date;

public record HorarioDtoRequest(
        LocalTime horaInicio,
        LocalTime horaFin
) { }
