package edu.unimagdalena.reservasespacios.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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
    private Long sedeId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "sede")
    private List<Espacio> espacios;

}
