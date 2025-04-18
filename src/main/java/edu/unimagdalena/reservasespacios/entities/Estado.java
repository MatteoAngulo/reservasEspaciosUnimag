package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

public class Estado {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;

    @OneToMany(mappedBy = "estado")
    private List<Problema> problemas;

    @OneToMany(mappedBy = "estado")
    private List<Reserva> reservas;


    @Column
    @NotBlank
    private String nombre;
}
