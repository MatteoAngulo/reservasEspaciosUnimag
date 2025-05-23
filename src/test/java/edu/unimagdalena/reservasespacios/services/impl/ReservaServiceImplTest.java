package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.ReservaMapper;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import edu.unimagdalena.reservasespacios.enums.EstadoReserva;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import edu.unimagdalena.reservasespacios.repositories.HorarioEspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServiceImplTest {

    @Mock
    private ReservaRepository repository;

    @Mock
    private ReservaMapper mapper;

    @Mock
    private HorarioEspacioRepository horarioEspacioRepository;

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private ReservaServiceImpl servicio;

    private Reserva reserva1;
    private Reserva reserva2;
    private HorarioEspacio horarioEspacio1;
    private HorarioEspacio horarioEspacio2;
    private ReservaDtoResponse dtoResponse1;
    private ReservaDtoResponse dtoResponse2;
    private Estudiante estudiante1;
    private Estudiante estudiante2;



    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);

        horarioEspacio1 = HorarioEspacio.builder()
                .idHorarioEspacio(1L)
                .dia(DayOfWeek.TUESDAY)
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(10, 0))
                .build();

        horarioEspacio2 = HorarioEspacio.builder()
                .idHorarioEspacio(2L)
                .dia(DayOfWeek.WEDNESDAY)
                .horaInicio(LocalTime.of(8, 30))
                .horaFin(LocalTime.of(10, 30))
                .build();

        estudiante1 = Estudiante.builder()
                .idEstudiante(1L)
                .nombre("JUAN PÉREZ")
                .build();

        estudiante2 = Estudiante.builder()
                .idEstudiante(2L)
                .nombre("JOSEPH FERRER")
                .build();

        reserva1 = Reserva.builder()
                .idReserva(1L)
                .estadoReserva(EstadoReserva.PENDIENTE)
                .horarioEspacio(horarioEspacio1)
                .estudiante(estudiante1)
                .motivo("RECREACIÓN")
                .fecha(LocalDate.of(2025, 5, 28))
                .build();

        reserva2 = Reserva.builder()
                .idReserva(2L)
                .estadoReserva(EstadoReserva.APROBADO)
                .horarioEspacio(horarioEspacio2)
                .estudiante(estudiante2)
                .motivo("ESTUDIO")
                .fecha(LocalDate.of(2025, 5, 30))
                .build();
    }

    @Test
    void findAllReservas() {
        when(repository.findAll()).thenReturn(List.of(reserva1, reserva2));

    }

    @Test
    void findReservaById() {
    }

    @Test
    void findReservasPorEstudiante() {
    }

    @Test
    void findReservasPorEstado() {
    }

    @Test
    void findReservaPorHorarioEspacioYFecha() {
    }

    @Test
    void saveReservaAdmin() {
    }

    @Test
    void updateReservaAdmin() {
    }

    @Test
    void saveReservaEstudiante() {
    }

    @Test
    void updateReservaEstudiante() {
    }

    @Test
    void cancelarReserva() {
    }

    @Test
    void aprobarReserva() {
    }

    @Test
    void rechazarReserva() {
    }
}