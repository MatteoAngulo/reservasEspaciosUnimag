package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@MappedSuperclass
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long usuarioId;
    protected String correo;
    protected String contrasena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolId", referencedColumnName = "rolId")
    private Rol rol;
}
