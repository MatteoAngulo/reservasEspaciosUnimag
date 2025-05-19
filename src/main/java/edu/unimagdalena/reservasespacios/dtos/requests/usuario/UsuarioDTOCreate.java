package edu.unimagdalena.reservasespacios.dtos.requests.usuario;

import edu.unimagdalena.reservasespacios.enums.Rol;

public record UsuarioDTOCreate(
        String name,
        String correo,
        String contrasena,
        Rol rol
) {
}
