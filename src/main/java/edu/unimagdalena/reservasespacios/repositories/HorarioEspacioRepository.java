package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface HorarioEspacioRepository extends JpaRepository<HorarioEspacio, Long> {

    List<HorarioEspacio> findHorarioEspacioByDia(DayOfWeek dia);
    List<HorarioEspacio> findHorarioEspacioByEspacio_IdEspacio(Long id);
    List<HorarioEspacio> findHorarioEspaciosByDiaAndEspacio_IdEspacio(DayOfWeek dia, Long id);

}
