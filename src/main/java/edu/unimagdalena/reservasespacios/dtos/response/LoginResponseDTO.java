package edu.unimagdalena.reservasespacios.dtos.response;

import edu.unimagdalena.reservasespacios.enums.RolEnum;

public record LoginResponseDTO(
        String token,
        RolEnum rol,
        Long idEstudiante
) {
}
