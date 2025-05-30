package edu.unimagdalena.reservasespacios.entities;
import edu.unimagdalena.reservasespacios.enums.TipoEspacio;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "espacios")
public class Espacio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspacio;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEspacio tipo;

    @Column
    private String restricciones;

    @Column
    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "sedeId")
    private Sede sede;

    @OneToMany(mappedBy = "espacio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problema> problemas;

    @OneToMany(mappedBy = "espacio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioEspacio> horariosEspacio = new ArrayList<>();
}
