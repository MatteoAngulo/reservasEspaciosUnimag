package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.enums.Rol;

public record UsuarioDTOResponse(
        String name,
        Rol rol
) {
}
