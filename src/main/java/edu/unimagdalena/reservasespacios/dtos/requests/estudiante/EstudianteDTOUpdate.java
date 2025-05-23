package edu.unimagdalena.reservasespacios.dtos.requests.estudiante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record EstudianteDTOUpdate(
        @NotNull(message = "El código estudiantil no puede ser nulo")
        Long codigoEstudiantil,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String nombre,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contrasena
) {
}
