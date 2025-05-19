package edu.unimagdalena.reservasespacios.entities;

import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
public class Reserva {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "idEstudiante")
    private Estudiante estudiante;
    
    @OneToMany(mappedBy = "reserva")
    private List<HistorialReserva> historialReservas;

    @OneToOne
    @JoinColumn(name = "id_horario_espacio", referencedColumnName = "idHorarioEspacio")
    private HorarioEspacio horarioEspacio;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoReserva estadoReserva;

    @Column
    @Size(min = 5, max = 1000)
    private String motivo;


}
