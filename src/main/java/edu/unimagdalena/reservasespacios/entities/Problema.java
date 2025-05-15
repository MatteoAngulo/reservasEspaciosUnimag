package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
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

public class Problema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProblema;

    @ManyToOne
    @JoinColumn(name = "id_espacio", referencedColumnName = "idEspacio")
    private Espacio espacio;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadoProblema estado;

    @Column(nullable = false)
    @Size(min = 5, max = 250)
    private String descripcion;

    @Column(nullable = false)
    private Date fecha;



}
