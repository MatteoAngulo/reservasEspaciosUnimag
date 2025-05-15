package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Reserva {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "idEstudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_horario", referencedColumnName = "idHorario")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "id_espacio", referencedColumnName = "idEspacio")
    private Espacio espacio;
    
    @OneToMany(mappedBy = "reserva")
    private List<HistorialReserva> historialReservas;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @Column
    @Future
    private Date fecha;

    @Column
    @Size(min = 5, max = 1000)
    private String motivo;


}
