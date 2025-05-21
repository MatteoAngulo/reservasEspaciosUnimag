package edu.unimagdalena.reservasespacios.exceptions;

import java.time.LocalDate;

public class ReservaExistenteException extends RuntimeException {
    public ReservaExistenteException(LocalDate fecha, Long idHorarioEspacio) {
        super("Ya existe una reserva con la fecha " + fecha + " e idHorarioEspacio " + idHorarioEspacio);
    }
}
