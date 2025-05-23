package edu.unimagdalena.reservasespacios.dtos.requests.usuario;

import edu.unimagdalena.reservasespacios.enums.RolEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioDTOCreate(
        @NotBlank(message = "El correo es obligatorio")
        @Size(max = 150, message = "El correo no debe superar los 100 caracteres")
        String nombre,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Debe ser un correo válido")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contrasena,

        @NotNull(message = "El rol es obligatorio")
        RolEnum rol
) {
}
