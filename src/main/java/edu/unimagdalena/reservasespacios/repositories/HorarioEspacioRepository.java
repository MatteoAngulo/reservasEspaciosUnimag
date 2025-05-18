package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HorarioEspacioRepository extends JpaRepository<HorarioEspacio, Long> {

    @Query("SELECT h FROM HorarioEspacio as h WHERE h.fecha = :fecha AND h.espacio.idEspacio = :idEspacio " +
            "AND h.estadoEspacio = 'DISPONIBLE'")
    List<HorarioEspacio> findHorariosDisponiblesPorFechaYEspacio(@Param("fecha") LocalDate fecha,
                                                                 @Param("idEspacio") Long idEspacio);

    @Query("SELECT h FROM HorarioEspacio as h WHERE h.fecha = :fecha AND h.espacio.idEspacio = :idEspacio ")
    List<HorarioEspacio> findHorariosExistentes(@Param("fecha") LocalDate fecha,
                                                 @Param("idEspacio") Long idEspacio);

}
