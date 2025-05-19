package edu.unimagdalena.reservasespacios.repositories;

import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findReservaByEstudianteIdEstudiante(Long id);
    List<Reserva> findReservaByEstadoReserva(EstadoReserva estadoReserva);
}
