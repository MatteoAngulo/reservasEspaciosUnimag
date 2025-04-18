package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Usuario {

    @NotBlank
    private String nombre;

    @Email
    private String correo;

    @NotBlank
    private String contrasena;

    @NotBlank
    private String rol;

    public abstract void iniciarSesion();

}
