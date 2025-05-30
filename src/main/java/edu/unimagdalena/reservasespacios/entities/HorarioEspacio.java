package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class HorarioEspacio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorarioEspacio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_espacio", referencedColumnName = "idEspacio")
    private Espacio espacio;

    @OneToMany(mappedBy = "horarioEspacio")
    private List<Reserva> reservas;


    @Enumerated(EnumType.STRING)
    private DayOfWeek dia;

    @Column
    LocalTime horaInicio;

    @Column
    LocalTime horaFin;
}
