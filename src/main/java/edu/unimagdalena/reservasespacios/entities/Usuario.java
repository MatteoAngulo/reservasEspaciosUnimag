package edu.unimagdalena.reservasespacios.entities;

import edu.unimagdalena.reservasespacios.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Entity
@Data
@NoArgsConstructor
public class Usuario {
 // las validaciones se hacen en los dtos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @Email
    private String correo;

    @NotBlank
    private String contrasena;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
