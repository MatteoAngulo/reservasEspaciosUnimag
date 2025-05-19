package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.ReservaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;

import java.util.List;

public interface ReservaService {
    List<ReservaDtoResponse> findAllReservas();
    ReservaDtoResponse findReservaById(Long id);
    List<ReservaDtoResponse> findReservasPorEstudiante(Long idEstudiante);
    List<ReservaDtoResponse> findReservasPorEstado(EstadoReserva estado);

    ReservaDtoResponse updateReserva(Long id, ReservaDtoRequest reservaDto);
    ReservaDtoResponse saveReserva(ReservaDtoRequest reservaDto);

    ReservaDtoResponse confirmarReserva(Long idReserva);
    ReservaDtoResponse cancelarReserva(Long idReserva);
    ReservaDtoResponse aprobarReserva(Long idReserva);
    ReservaDtoResponse rechazarReserva(Long idReserva, String motivoRechazo);



}
