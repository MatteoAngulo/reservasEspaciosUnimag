package edu.unimagdalena.reservasespacios.entities;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "espacios")
public class Espacio {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    //@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String tipo;

    @Column
    private String restricciones;

    @ManyToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "id")
    private Sede sede;

    @OneToMany(mappedBy = "espacio")
    private List<EspacioDisponible> espaciosDisponibles;

    @OneToMany(mappedBy = "espacio")
    private List<Problema> problemas;
}
