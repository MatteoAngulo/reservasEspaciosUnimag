package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}
