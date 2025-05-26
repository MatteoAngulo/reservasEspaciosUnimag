package edu.unimagdalena.reservasespacios.entities;

import edu.unimagdalena.reservasespacios.enums.RolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Rol {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolId;
    @Enumerated(EnumType.STRING)
    private RolEnum rolEnum;

    @OneToMany(mappedBy = "rol",fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}
