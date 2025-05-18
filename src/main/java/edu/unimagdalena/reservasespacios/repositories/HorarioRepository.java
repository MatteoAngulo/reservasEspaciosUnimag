package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    boolean existsByHoraInicioAndHoraFin(LocalTime horaInicio, LocalTime horaFin);
}


