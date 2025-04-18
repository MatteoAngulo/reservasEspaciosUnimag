package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class EspacioDisponible {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspacioDisponible;

    @ManyToOne
    @JoinColumn(name = "id_horario", referencedColumnName = "idHorario")
    private Horario horario;

    @OneToOne(mappedBy = "espacioDisponible")
    private Reserva reservas;

    @ManyToOne
    @JoinColumn(name = "id_espacio", referencedColumnName = "idEspacio")
    private Espacio espacio;

}
