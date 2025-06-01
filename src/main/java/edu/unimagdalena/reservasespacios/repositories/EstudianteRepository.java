package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByCodigoEstudiantil(Long codigoEstudiantil);
    void deleteByCodigoEstudiantil(Long codigo);


    Optional<Estudiante> findByUsuarioUsuarioId(Long idUsuario);
}
