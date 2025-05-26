package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Problema;
import edu.unimagdalena.reservasespacios.enums.EstadoProblema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* findAll(), findById(ID id), save(T entity),
deleteById(ID id), deleteAll(), count(), existsById(ID id),
findAll(Pageable pageable) ← para paginación
findAll(Sort sort) ← para ordenamiento
 */

public interface ProblemaRepository extends JpaRepository<Problema, Long> {
    List<Problema> findByEstudiante_IdEstudiante(Long idEst);
    List<Problema> findByEstado(EstadoProblema estado);
    List<Problema> findByEspacio_IdEspacio(Long idEspacio);

}
