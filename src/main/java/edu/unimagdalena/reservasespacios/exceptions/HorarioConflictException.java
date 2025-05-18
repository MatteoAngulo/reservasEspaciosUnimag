package edu.unimagdalena.reservasespacios.exceptions;

import java.time.LocalTime;

public class HorarioConflictException extends RuntimeException {
    public HorarioConflictException(LocalTime inicio, LocalTime fin) {
        super("Ya existe un horario registrado con el rango " + inicio + " - " + fin);
    }
}
