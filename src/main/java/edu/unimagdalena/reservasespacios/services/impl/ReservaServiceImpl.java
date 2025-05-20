package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.ReservaMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.ReservaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ReservaNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.ReservaRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.HorarioEspacioService;
import edu.unimagdalena.reservasespacios.services.interfaces.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final HorarioEspacioService horarioEspacioService;

    @Override
    public List<ReservaDtoResponse> findAllReservas() {
        return reservaRepository.findAll().stream()
                .map(reservaMapper::toReservaDtoResponse)
                .toList();
    }

    @Override
    public ReservaDtoResponse findReservaById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva con ID: " + id+ " no encontrado"));

        return reservaMapper.toReservaDtoResponse(reserva);
    }

    @Override
    public List<ReservaDtoResponse> findReservasPorEstudiante(Long idEstudiante) {
        return reservaRepository.findReservaByEstudianteIdEstudiante(idEstudiante)
                .stream()
                .map(reservaMapper::toReservaDtoResponse)
                .toList();
    }

    @Override
    public List<ReservaDtoResponse> findReservasPorEstado(EstadoReserva estado) {
        return reservaRepository.findReservaByEstadoReserva(estado)
                .stream()
                .map(reservaMapper::toReservaDtoResponse)
                .toList();
    }

    @Override
    public ReservaDtoResponse saveReserva(ReservaDtoRequest reservaDto) {
        return null;
    }

    @Override
    public ReservaDtoResponse updateReserva(Long id, ReservaDtoRequest reservaDto) {
        return null;
    }

    @Override
    public ReservaDtoResponse confirmarReserva(Long idReserva) {
        return null;
    }

    @Override
    public ReservaDtoResponse cancelarReserva(Long idReserva) {
        return null;
    }

    @Override
    public ReservaDtoResponse aprobarReserva(Long idReserva) {
        return null;
    }

    @Override
    public ReservaDtoResponse rechazarReserva(Long idReserva, String motivoRechazo) {
        return null;
    }
}
