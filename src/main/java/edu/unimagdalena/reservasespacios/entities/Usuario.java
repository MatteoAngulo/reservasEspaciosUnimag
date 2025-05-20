package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
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
    private Long usuarioId;
    private String nombre;
    private String correo;
    private String contrasena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolId", referencedColumnName = "rolId")
    private Rol rol;
}
