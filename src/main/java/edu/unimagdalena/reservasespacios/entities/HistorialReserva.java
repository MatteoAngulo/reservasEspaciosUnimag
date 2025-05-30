package edu.unimagdalena.reservasespacios.entities;

import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class HistorialReserva {
    //las validaciones se hacen en los dtos

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", referencedColumnName = "idReserva")
    private Reserva reserva;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva", nullable = false)
    private EstadoReserva estadoReserva;

    @Column
    private LocalDateTime fechaCambio;

    @Size(max = 256)
    @Column
    private String comentario;
}
