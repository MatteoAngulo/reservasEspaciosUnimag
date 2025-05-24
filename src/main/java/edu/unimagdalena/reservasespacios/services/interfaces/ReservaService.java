package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaAccionesDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaEstDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaUpdateDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaEstDtoResponse;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {
    List<ReservaDtoResponse> findAllReservas();
    ReservaDtoResponse findReservaById(Long id);
    List<ReservaDtoResponse> findReservasPorEstudiante(Long idEstudiante);
    List<ReservaDtoResponse> findReservasPorEstado(EstadoReserva estado);
    ReservaDtoResponse findReservaPorHorarioEspacioYFecha(LocalDate fecha, Long idHorarioEspacio);

    ReservaDtoResponse updateReservaAdmin(Long idReserva, ReservaUpdateDtoRequest reservaDto);
    ReservaDtoResponse saveReservaAdmin(ReservaDtoRequest reservaDto);

    ReservaEstDtoResponse saveReservaEstudiante(ReservaEstDtoRequest dto, Long idEst);
    ReservaEstDtoResponse updateReservaEstudiante(Long idReserva, ReservaEstDtoRequest dto);

    ReservaDtoResponse cancelarReserva(ReservaAccionesDtoRequest dto, Long idReserva);
    ReservaDtoResponse aprobarReserva(Long idReserva);
    ReservaDtoResponse rechazarReserva(Long idReserva, ReservaAccionesDtoRequest dtoRequest);



}
