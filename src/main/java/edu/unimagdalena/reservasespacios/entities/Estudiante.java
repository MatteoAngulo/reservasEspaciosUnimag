package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder


public class Estudiante extends Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;

    @OneToMany(mappedBy = "estudiante")
    private List<Reserva> reservas;

    @Override
    public void iniciarSesion() {

    }
}
