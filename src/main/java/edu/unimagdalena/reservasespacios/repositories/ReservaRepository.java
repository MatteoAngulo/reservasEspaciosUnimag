package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.entities.Reserva;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findReservaByEstudianteIdEstudiante(Long id);
    List<Reserva> findReservaByEstadoReserva(EstadoReserva estadoReserva);
    Optional<Reserva> findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(LocalDate fecha, Long id);
}
