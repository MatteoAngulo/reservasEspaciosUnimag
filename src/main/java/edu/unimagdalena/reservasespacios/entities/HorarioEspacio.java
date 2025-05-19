package edu.unimagdalena.reservasespacios.entities;

import edu.unimagdalena.reservasespacios.enums.EstadoEspacio;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class HorarioEspacio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorarioEspacio;

    @OneToOne
    @JoinColumn(name = "id_horario", referencedColumnName = "idHorario")
    private Horario horario;

    @OneToOne
    @JoinColumn(name = "id_espacio", referencedColumnName = "idEspacio")
    private Espacio espacio;

    @OneToOne(mappedBy = "horarioEspacio")
    private Reserva reserva;

    @Enumerated(EnumType.STRING)
    private EstadoEspacio estadoEspacio;

    @Future // las validaciones se hacen en los dtos
    @Column
    private LocalDate fecha;
}
