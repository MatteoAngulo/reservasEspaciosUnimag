package edu.unimagdalena.reservasespacios.exceptions;

import java.time.LocalTime;

public class HorarioSolapadoException extends RuntimeException {
    public HorarioSolapadoException(Long idHorario, LocalTime inicio, LocalTime fin) {

      super("El horario solapa con uno existente (ID: " + idHorario + " RANGO: " + inicio + " - " + fin + ")");
    }
}
