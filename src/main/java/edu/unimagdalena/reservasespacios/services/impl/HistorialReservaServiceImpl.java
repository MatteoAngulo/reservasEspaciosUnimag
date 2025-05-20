package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.HistorialReservaMapper;
import edu.unimagdalena.reservasespacios.dtos.response.HistorialReservaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.HistorialReserva;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.exceptions.notFound.HistorialNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.HistorialReservaRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.HistorialReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialReservaServiceImpl implements HistorialReservaService {

    private final HistorialReservaRepository historialReservaRepository;
    private final HistorialReservaMapper historialReservaMapper;

    @Override
    public HistorialReservaDtoResponse registrarCambioReserva(Reserva reserva, EstadoReserva estadoReserva, String comentario) {
        HistorialReserva historial = new HistorialReserva();
        historial.setReserva(reserva);
        historial.setEstadoReserva(estadoReserva);
        historial.setComentario(comentario);
        historial.setFechaCambio(LocalDateTime.now());

        return historialReservaMapper.toDtoResponse(historialReservaRepository.save(historial));
    }

    @Override
    public HistorialReservaDtoResponse findHistorialById(Long id) {
        HistorialReserva historial = historialReservaRepository.findById(id)
                .orElseThrow(() -> new HistorialNotFoundException("El historial con ID: " + id + " no encontrado"));

        return historialReservaMapper.toDtoResponse(historial);
    }

    @Override
    public List<HistorialReservaDtoResponse> findAllHistorial() {
        return historialReservaRepository.findAll().stream()
                .map(historialReservaMapper::toDtoResponse)
                .toList();
    }
}
