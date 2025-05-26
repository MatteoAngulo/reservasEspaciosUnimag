package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;

    @NotNull
    @Column(nullable = false, unique = true)
    private Long codigoEstudiantil;
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuarioId")
    private Usuario usuario;

    @OneToMany(mappedBy = "estudiante",fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "estudiante",fetch = FetchType.LAZY)
    private List<Problema> problemas;
}
