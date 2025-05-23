package edu.unimagdalena.reservasespacios.dtos.requests;


public record EspacioDTOResquests (
        String nombre,
        String tipo,
        String restricciones,
        Long idSede,
        Boolean disponible
){}
