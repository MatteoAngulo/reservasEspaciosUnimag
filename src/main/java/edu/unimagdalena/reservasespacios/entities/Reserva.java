package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Reserva {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "idEstado")
    private Estado estado;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_espacio_disponible", referencedColumnName = "idEspacioDisponible")
    private EspacioDisponible espacioDisponible;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "idEstudiante")
    private Estudiante estudiante;

    @Column
    @Future
    private Date fecha;

    @Column
    @Size(min = 5, max = 1000)
    private String motivo;


}
