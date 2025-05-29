package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Rol;
import edu.unimagdalena.reservasespacios.enums.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByRolEnum(RolEnum rol);
}
