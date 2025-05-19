package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Problema;
import org.springframework.data.jpa.repository.JpaRepository;

/* findAll(), findById(ID id), save(T entity),
deleteById(ID id), deleteAll(), count(), existsById(ID id),
findAll(Pageable pageable) ← para paginación
findAll(Sort sort) ← para ordenamiento
 */

public interface ProblemaRepository extends JpaRepository<Problema, Long> {

}
