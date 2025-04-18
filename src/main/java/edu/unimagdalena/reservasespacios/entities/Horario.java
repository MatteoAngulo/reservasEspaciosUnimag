package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Horario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    @OneToMany(mappedBy = "espacio")
    private List<EspacioDisponible> espaciosDisponibles;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;
}
