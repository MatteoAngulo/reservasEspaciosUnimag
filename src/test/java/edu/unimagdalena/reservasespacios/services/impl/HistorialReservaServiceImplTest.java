package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.HistorialReservaMapper;
import edu.unimagdalena.reservasespacios.dtos.response.HistorialReservaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.HistorialReserva;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.exceptions.notFound.HistorialNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.HistorialReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HistorialReservaServiceImplTest {

    @Mock
    private HistorialReservaRepository repositorio;

    @Mock
    private HistorialReservaMapper mapper;

    @InjectMocks
    private HistorialReservaServiceImpl servicio;

    private Reserva reserva;
    private HistorialReserva historial;
    private HistorialReservaDtoResponse historialDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        reserva = Reserva.builder()
                .idReserva(1L)
                .estadoReserva(EstadoReserva.PENDIENTE)
                .motivo("Clase especial")
                .fecha(LocalDate.now())
                .build();

        historial = new HistorialReserva();
        historial.setIdHistorial(1L);
        historial.setReserva(reserva);
        historial.setEstadoReserva(EstadoReserva.PENDIENTE);
        historial.setComentario("Clase especial");
        historial.setFechaCambio(LocalDateTime.now());

        historialDto = new HistorialReservaDtoResponse(
                1L,
                1L,
                EstadoReserva.PENDIENTE,
                historial.getFechaCambio(),
                "Clase especial"
        );
    }

    @Test
    void registrarCambioReserva() {

        when(repositorio.save(any(HistorialReserva.class))).thenReturn(historial);
        when(mapper.toDtoResponse(any(HistorialReserva.class))).thenReturn(historialDto);

        HistorialReservaDtoResponse result = servicio.registrarCambioReserva(
                reserva,
                EstadoReserva.PENDIENTE,
                "Clase especial"
        );

        assertNotNull(result);
        assertEquals(historialDto.idHistorial(), result.idHistorial());
        assertEquals(historialDto.estadoReserva(), result.estadoReserva());
        verify(repositorio).save(any(HistorialReserva.class));
        verify(mapper).toDtoResponse(any(HistorialReserva.class));
    }

    @Test
    void testFindHistorialById_Exists() {
        when(repositorio.findById(1L)).thenReturn(Optional.of(historial));
        when(mapper.toDtoResponse(historial)).thenReturn(historialDto);

        HistorialReservaDtoResponse result = servicio.findHistorialById(1L);

        assertNotNull(result);
        assertEquals(historialDto.idHistorial(), result.idHistorial());
        verify(repositorio).findById(1L);
        verify(mapper).toDtoResponse(historial);
    }

    @Test
    void testFindHistorialById_NotFound() {
        when(repositorio.findById(99L)).thenReturn(Optional.empty());

        assertThrows(HistorialNotFoundException.class, () -> {
            servicio.findHistorialById(99L);
        });

        verify(repositorio).findById(99L);
    }

    @Test
    void testFindAllHistorial() {
        when(repositorio.findAll()).thenReturn(List.of(historial));
        when(mapper.toDtoResponse(historial)).thenReturn(historialDto);

        List<HistorialReservaDtoResponse> result = servicio.findAllHistorial();

        assertEquals(1, result.size());
        assertEquals(historialDto.idHistorial(), result.get(0).idHistorial());
        verify(repositorio).findAll();
        verify(mapper).toDtoResponse(historial);
    }
}