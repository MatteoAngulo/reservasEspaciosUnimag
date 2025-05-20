package edu.unimagdalena.reservasespacios.exceptions;

import java.time.LocalTime;

public class HorasConflictException extends RuntimeException {
    public HorasConflictException(LocalTime horaInicio, LocalTime horaFin) {

        super("Hay un problema con la franja horaria insertada (" + horaInicio + " - " + horaFin);
    }
}
