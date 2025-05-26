package edu.unimagdalena.reservasespacios.dtos.response;

import java.sql.Date;

public record ProblemaDtoResponse (
        Long idProblema,
        Long espacioId,
        String estado,
        String descripcion,
        Date fecha,
        Long idEstudiante
){}
