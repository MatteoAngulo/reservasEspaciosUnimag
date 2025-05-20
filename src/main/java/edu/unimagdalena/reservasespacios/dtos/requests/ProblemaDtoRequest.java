package edu.unimagdalena.reservasespacios.dtos.requests;

import java.sql.Date;

public record ProblemaDtoRequest (
        Long espacioId,
        String estado,
        String descripcion,
        Date fecha
){}
