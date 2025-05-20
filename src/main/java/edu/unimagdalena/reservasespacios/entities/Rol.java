package edu.unimagdalena.reservasespacios.entities;

import edu.unimagdalena.reservasespacios.enums.RolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rol {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolId;
    private RolEnum rolEnum;

    @OneToMany(mappedBy = "rol",fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}
