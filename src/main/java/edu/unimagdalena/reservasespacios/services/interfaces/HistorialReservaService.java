package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.response.HistorialReservaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;

import java.util.List;

public interface HistorialReservaService {

    HistorialReservaDtoResponse registrarCambioReserva(Reserva reserva, EstadoReserva estadoReserva, String comentario);
    HistorialReservaDtoResponse findHistorialById(Long id);
    List<HistorialReservaDtoResponse> findAllHistorial();


}
