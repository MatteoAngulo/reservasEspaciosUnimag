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
public class Estudiante extends Usuario {

    @NotNull
    @Column(nullable = false, unique = true)
    private Long codigoEstudiantil;

    @OneToMany(mappedBy = "estudiante",fetch = FetchType.LAZY)
    private List<Reserva> reservas;
}
