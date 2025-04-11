package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
findAll(), findById(ID id), save(T entity),
deleteById(ID id), deleteAll(), count(), existsById(ID id),
findAll(Pageable pageable) ← para paginación
findAll(Sort sort) ← para ordenamiento
*/
public interface EspacioRepository extends JpaRepository<Espacio, Long> {
    //save para crearEspacio
}
