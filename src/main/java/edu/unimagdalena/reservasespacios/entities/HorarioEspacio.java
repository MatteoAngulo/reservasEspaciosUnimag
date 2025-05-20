package edu.unimagdalena.reservasespacios.entities;

import edu.unimagdalena.reservasespacios.enums.EstadoEspacio;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    @OneToOne
    @JoinColumn(name = "id_espacio", referencedColumnName = "idEspacio")
    private Espacio espacio;

    @OneToOne(mappedBy = "horarioEspacio")
    private Reserva reserva;

    @Enumerated(EnumType.STRING)
    private EstadoEspacio estadoEspacio;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> listaDias;

    @Column
    LocalTime horaInicio;

    @Column
    LocalTime horaFin;
}
