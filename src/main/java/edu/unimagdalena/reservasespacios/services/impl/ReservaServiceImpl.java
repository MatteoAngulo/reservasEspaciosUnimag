package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.ReservaMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaCambioEstadoDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaEstDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaUpdateDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaEstDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.exceptions.FechaInvalidaParaHorarioException;
import edu.unimagdalena.reservasespacios.exceptions.IdInvalidoException;
import edu.unimagdalena.reservasespacios.exceptions.ProblemaEstadoReservaException;
import edu.unimagdalena.reservasespacios.exceptions.ReservaExistenteException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EstudianteNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.HorarioEspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ReservaNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ReservaPorHorarioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import edu.unimagdalena.reservasespacios.repositories.HorarioEspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.ReservaRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.HistorialReservaService;
import edu.unimagdalena.reservasespacios.services.interfaces.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final HorarioEspacioRepository horarioEspacioRepository;
    private final HistorialReservaService historialReservaService;
    private final EstudianteRepository estudianteRepository;

    @Override
    //Solo Admins
    public List<ReservaDtoResponse> findAllReservas() {
        return reservaRepository.findAll().stream()
                .map(reservaMapper::toReservaDtoResponse)
                .toList();
    }

    //Ambos
    @Override
    public ReservaDtoResponse findReservaById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));

        return reservaMapper.toReservaDtoResponse(reserva);
    }

    //Ambos
    @Override
    public List<ReservaDtoResponse> findReservasPorEstudiante(Long idEstudiante) {
        return reservaRepository.findReservaByEstudianteIdEstudiante(idEstudiante)
                .stream()
                .map(reservaMapper::toReservaDtoResponse)
                .toList();
    }

    //Admin
    @Override
    public List<ReservaDtoResponse> findReservasPorEstado(EstadoReserva estado) {
        return reservaRepository.findReservaByEstadoReserva(estado)
                .stream()
                .map(reservaMapper::toReservaDtoResponse)
                .toList();
    }

    //Admin
    @Override
    public ReservaDtoResponse findReservaPorHorarioEspacioYFecha(LocalDate fecha, Long idHorarioEspacio) {
        Reserva reserva = reservaRepository.findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(fecha, idHorarioEspacio)
                .orElseThrow(() -> new ReservaPorHorarioNotFoundException("La reserva con fecha " + fecha + " y horario-espacio " + idHorarioEspacio + " no encontrada"));

        return reservaMapper.toReservaDtoResponse(reserva);

    }

    //Admin
    @Override
    public ReservaDtoResponse saveReservaAdmin(ReservaDtoRequest reservaDto) {

        Estudiante estudiante = estudianteRepository.findById(reservaDto.idEstudiante())
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante con ID: " + reservaDto.idEstudiante() + " no encontrado"));

        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(reservaDto.idHorarioEspacio())
                .orElseThrow(() -> new HorarioEspacioNotFoundException("Horario-espacio con ID: "+ reservaDto.idHorarioEspacio() + " no encontrado"));

        validarFechaConDiaHorario(reservaDto.fecha(), horarioEspacio);

        validarReservaDisponible(reservaDto.fecha(), reservaDto.idHorarioEspacio());

        Reserva reserva = reservaMapper.toEntity(reservaDto);

        reserva.setEstudiante(estudiante);
        reserva.setHorarioEspacio(horarioEspacio);
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);

        Reserva reservaGuardada = reservaRepository.save(reserva);

        // Se usa el motivo de la reserva como comentario inicial en el historial
        historialReservaService.registrarCambioReserva(reservaGuardada, reservaGuardada.getEstadoReserva(), reservaGuardada.getMotivo());

        return reservaMapper.toReservaDtoResponse(reservaGuardada);

    }

    //Admin
    @Override
    public ReservaDtoResponse updateReservaAdmin(Long id, ReservaUpdateDtoRequest reservaDto) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));

        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(reservaDto.idHorarioEspacio())
                .orElseThrow(() -> new HorarioEspacioNotFoundException("El horario-espacio con ID: " + reservaDto.idHorarioEspacio() + " no encontrado"));

        validarFechaConDiaHorario(reservaDto.fecha(), horarioEspacio);

        validarReservaDisponible(reservaDto.fecha(), reservaDto.idHorarioEspacio());

        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
        reserva.setHorarioEspacio(horarioEspacio);
        reserva.setFecha(reservaDto.fecha());
        reserva.setMotivo(reservaDto.motivo());

        Reserva reservaActu = reservaRepository.save(reserva);

        historialReservaService.registrarCambioReserva(reservaActu, reservaActu.getEstadoReserva(), reservaActu.getMotivo());

        return reservaMapper.toReservaDtoResponse(reservaActu);

    }

    //Estudiante
    @Override
    public ReservaEstDtoResponse saveReservaEstudiante(ReservaEstDtoRequest dto, Long idEst) {

        Estudiante estudiante = estudianteRepository.findById(idEst)
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante con ID: " +idEst+" no encontrado"));

        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(dto.idHorarioEspacio())
                .orElseThrow(() -> new HorarioEspacioNotFoundException("El horario-espacio con ID: " + dto.idHorarioEspacio() + " no encontrado"));

        validarFechaConDiaHorario(dto.fecha(), horarioEspacio);

        validarReservaDisponible(dto.fecha(), dto.idHorarioEspacio());

        Reserva reserva = reservaMapper.toEntityEst(dto);

        reserva.setEstudiante(estudiante);
        reserva.setHorarioEspacio(horarioEspacio);
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);

        Reserva reservaGuardada = reservaRepository.save(reserva);

        // Se usa el motivo de la reserva como comentario inicial en el historial
        historialReservaService.registrarCambioReserva(reservaGuardada, reservaGuardada.getEstadoReserva(), reservaGuardada.getMotivo());

        return reservaMapper.toReservaEstDtoResponse(reservaGuardada);
    }

    //Estudiante
    @Override
    public ReservaEstDtoResponse updateReservaEstudiante(Long idReserva, ReservaEstDtoRequest dto) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ReservaNotFoundException(idReserva));

        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(dto.idHorarioEspacio())
                .orElseThrow(() -> new HorarioEspacioNotFoundException("El horario-espacio con ID: " + dto.idHorarioEspacio() + " no encontrado"));

        validarFechaConDiaHorario(dto.fecha(), horarioEspacio);

        validarReservaDisponible(dto.fecha(), dto.idHorarioEspacio());

        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
        reserva.setHorarioEspacio(horarioEspacio);
        reserva.setFecha(dto.fecha());
        reserva.setMotivo(dto.motivo());

        Reserva reservaActu = reservaRepository.save(reserva);

        historialReservaService.registrarCambioReserva(reservaActu, reservaActu.getEstadoReserva(), reservaActu.getMotivo());

        return reservaMapper.toReservaEstDtoResponse(reservaActu);
    }

    //Ambos
    @Override
    public ReservaDtoResponse cancelarReserva(ReservaCambioEstadoDtoRequest dto, Long idReserva) {

        if(!dto.idReserva().equals(idReserva)){
            throw new IdInvalidoException("Los id's son diferentes");
        }

        Reserva reserva = reservaRepository.findById(dto.idReserva())
                .orElseThrow(() -> new ReservaNotFoundException(dto.idReserva()));

        if(reserva.getEstadoReserva() == EstadoReserva.RECHAZADO
        || reserva.getEstadoReserva() == EstadoReserva.CANCELADA){
            throw new ProblemaEstadoReservaException("No puede cancelar una reserva RECHAZADA o ya CANCELADA");
        }

        reserva.setEstadoReserva(EstadoReserva.CANCELADA);

        Reserva reservaCancelada = reservaRepository.save(reserva);

        historialReservaService.registrarCambioReserva(
                reservaCancelada,
                EstadoReserva.CANCELADA,
                dto.motivo());

        return reservaMapper.toReservaDtoResponse(reservaCancelada);
    }

    //Admin
    @Override
    public ReservaDtoResponse aprobarReserva(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ReservaNotFoundException(idReserva));

        if(reserva.getEstadoReserva() != EstadoReserva.PENDIENTE){
            throw new ProblemaEstadoReservaException("No puede aprobar una reserva en estado diferente a PENDIENTE");
        }

        reserva.setEstadoReserva(EstadoReserva.APROBADO);

        Reserva reservaAprobada = reservaRepository.save(reserva);

        historialReservaService.registrarCambioReserva(
                reservaAprobada,
                EstadoReserva.APROBADO,
                "Reserva aprobada");

        return reservaMapper.toReservaDtoResponse(reservaAprobada);
    }

    @Override
    public ReservaDtoResponse rechazarReserva(Long idReserva, ReservaCambioEstadoDtoRequest dtoRequest) {

        if(!dtoRequest.idReserva().equals(idReserva)){
            throw new IdInvalidoException("Los id's son diferentes");
        }

        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ReservaNotFoundException(idReserva));

        if(reserva.getEstadoReserva() != EstadoReserva.PENDIENTE){
            throw new ProblemaEstadoReservaException("No puede rechazar una reserva en estado diferente a PENDIENTE");
        }

        reserva.setEstadoReserva(EstadoReserva.RECHAZADO);

        Reserva reservaRechazada = reservaRepository.save(reserva);

        historialReservaService.registrarCambioReserva(
                reservaRechazada,
                EstadoReserva.RECHAZADO,
                dtoRequest.motivo());

        return reservaMapper.toReservaDtoResponse(reservaRechazada);
    }

    private void validarReservaDisponible(LocalDate fecha, Long idHorarioEspacio){
        Optional<Reserva> reservaExistente = reservaRepository.
                findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(fecha, idHorarioEspacio);

        if(reservaExistente.isPresent()){
            throw new ReservaExistenteException(fecha, idHorarioEspacio);
        }
    }

    private void validarFechaConDiaHorario(LocalDate fecha, HorarioEspacio horarioEspacio) {
        if (!horarioEspacio.getDia().equals(fecha.getDayOfWeek())) {
            throw new FechaInvalidaParaHorarioException("La fecha no corresponde al día del horario seleccionado.");
        }
    }
}
