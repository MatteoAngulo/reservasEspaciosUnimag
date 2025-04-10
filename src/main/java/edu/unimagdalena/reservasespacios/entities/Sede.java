package edu.unimagdalena.reservasespacios.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sedes")
public class Sede {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "sede")
    private Set<Espacio> espacios;

}
