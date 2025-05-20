package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.enums.RolEnum;

public record UsuarioDTOResponse(
        String nombre,
        RolEnum rol
) {
}
