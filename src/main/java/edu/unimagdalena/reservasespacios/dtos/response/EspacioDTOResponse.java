package edu.unimagdalena.reservasespacios.dtos.response;



public record EspacioDTOResponse (
        Long id,
        String nombre,
        String tipo,
        String restricciones,
        Long idSede,
        Boolean disponible
){}
