package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
 // las validaciones se hacen en los dtos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long usuarioId;
   protected String correo;
    protected String contrasena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolId", referencedColumnName = "rolId")
    private Rol rol;
}
