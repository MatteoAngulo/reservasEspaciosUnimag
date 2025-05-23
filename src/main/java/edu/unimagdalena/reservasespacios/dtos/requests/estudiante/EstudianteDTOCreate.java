package edu.unimagdalena.reservasespacios.dtos.requests.estudiante;

import edu.unimagdalena.reservasespacios.enums.RolEnum;
import lombok.Builder;

@Builder
public record EstudianteDTOCreate(
        Long codEstudiantil,
        String nombre,
        String correo,
        String contrasena,
        RolEnum rol
) {
}
