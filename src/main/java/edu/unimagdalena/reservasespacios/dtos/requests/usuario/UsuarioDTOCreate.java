package edu.unimagdalena.reservasespacios.dtos.requests.usuario;

import edu.unimagdalena.reservasespacios.enums.Rol;

public record UsuarioDTOCreate(
        String nombre,
        String correo,
        String contrasena,
        Rol rol
) {
}
