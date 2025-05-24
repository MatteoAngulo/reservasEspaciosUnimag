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
import edu.unimagdalena.reservasespacios.exceptions.notFound.ReservaPorHorarioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import edu.unimagdalena.reservasespacios.repositories.HorarioEspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.ReservaRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.HistorialReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    @Mock
    private HistorialReservaService historialReservaService;

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
                .codigoEstudiantil(2022114019L)
                .build();

        estudiante2 = Estudiante.builder()
                .idEstudiante(2L)
                .nombre("JOSEPH FERRER")
                .codigoEstudiantil(2022114064L)
                .build();

        reserva1 = Reserva.builder()
                .idReserva(1L)
                .estadoReserva(EstadoReserva.PENDIENTE)
                .horarioEspacio(horarioEspacio1)
                .estudiante(estudiante1)
                .motivo("RECREACIÓN")
                .fecha(LocalDate.of(2025, 5, 27))
                .build();

        reserva2 = Reserva.builder()
                .idReserva(2L)
                .estadoReserva(EstadoReserva.APROBADO)
                .horarioEspacio(horarioEspacio2)
                .estudiante(estudiante2)
                .motivo("ESTUDIO")
                .fecha(LocalDate.of(2025, 5, 28))
                .build();

        dtoResponse1 = new ReservaDtoResponse(
                1L,
                1L,
                1L,
                EstadoReserva.PENDIENTE,
                LocalDate.of(2025, 5, 27),
                "RECREACIÓN"
        );

        dtoResponse2 = new ReservaDtoResponse(
                2L,
                2L,
                2L,
                EstadoReserva.APROBADO,
                LocalDate.of(2025, 5, 28),
                "ESTUDIO"
        );


    }

    @Test
    void findAllReservas() {

        when(repository.findAll()).thenReturn(List.of(reserva1, reserva2));
        when(mapper.toReservaDtoResponse(reserva1)).thenReturn(dtoResponse1);
        when(mapper.toReservaDtoResponse(reserva2)).thenReturn(dtoResponse2);

        List<ReservaDtoResponse> resultado = servicio.findAllReservas();

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
        assertEquals("ESTUDIO", resultado.get(1).motivo());

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toReservaDtoResponse(reserva1);
        verify(mapper, times(1)).toReservaDtoResponse(reserva2);
    }

    @Test
    void findReservaById() {

        when(repository.findById(1L)).thenReturn(Optional.of(reserva1));
        when(mapper.toReservaDtoResponse(reserva1)).thenReturn(dtoResponse1);

        ReservaDtoResponse resultado = servicio.findReservaById(1L);

        assertEquals(EstadoReserva.PENDIENTE, resultado.estadoReserva());
        assertEquals(1, resultado.idReserva());

        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).toReservaDtoResponse(reserva1);
    }

    @Test
    void findReservasPorEstudiante() {

        when(repository.findReservaByEstudianteIdEstudiante(1L)).thenReturn(List.of(reserva1));
        when(mapper.toReservaDtoResponse(reserva1)).thenReturn(dtoResponse1);

        List<ReservaDtoResponse> result = servicio.findReservasPorEstudiante(1L);

        assertEquals(1, result.size());
        assertEquals(dtoResponse1, result.get(0));

        verify(repository, times(1)).findReservaByEstudianteIdEstudiante(1L);
        verify(mapper, times(1)).toReservaDtoResponse(reserva1);

    }

    @Test
    void findReservasPorEstado() {
        when(repository.findReservaByEstadoReserva(EstadoReserva.APROBADO)).thenReturn(List.of(reserva2));
        when(mapper.toReservaDtoResponse(reserva2)).thenReturn(dtoResponse2);

        List<ReservaDtoResponse> result = servicio.findReservasPorEstado(EstadoReserva.APROBADO);

        assertEquals(1, result.size());
        assertEquals(dtoResponse2, result.get(0));

        verify(repository, times(1)).findReservaByEstadoReserva(EstadoReserva.APROBADO);
        verify(mapper, times(1)).toReservaDtoResponse(reserva2);
    }

    @Test
    void findReservaPorHorarioEspacioYFecha() {
        when(repository.findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(LocalDate.of(2025, 5, 27), 1L))
                .thenReturn(Optional.of(reserva1));
        when(mapper.toReservaDtoResponse(reserva1)).thenReturn(dtoResponse1);

        ReservaDtoResponse result = servicio.findReservaPorHorarioEspacioYFecha(LocalDate.of(2025, 5, 27), 1L);

        assertEquals(dtoResponse1, result);

        verify(repository).findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(LocalDate.of(2025, 5, 27), 1L);
        verify(mapper).toReservaDtoResponse(reserva1);
    }

    @Test
    void testFindReservaPorHorarioEspacioYFechaNotFound() {
        when(repository.findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(LocalDate.of(2025, 5, 27), 1L))
                .thenReturn(Optional.empty());

        assertThrows(ReservaPorHorarioNotFoundException.class, () ->
                servicio.findReservaPorHorarioEspacioYFecha(LocalDate.of(2025, 5, 27), 1L)
        );
    }

    @Test
    void saveReservaAdmin() {

        ReservaDtoRequest dtoRequest = new ReservaDtoRequest(
                1L,
                1L,
                LocalDate.of(2025, 5, 27),
                "RECREACIÓN"
        );

        // Stubbing repositorios y mapeos
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante1));
        when(horarioEspacioRepository.findById(1L)).thenReturn(Optional.of(horarioEspacio1));
        when(repository.findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(dtoRequest.fecha(), dtoRequest.idHorarioEspacio()))
                .thenReturn(Optional.empty());
        when(mapper.toEntity(dtoRequest)).thenReturn(reserva1);
        when(repository.save(any(Reserva.class))).thenReturn(reserva1);
        when(mapper.toReservaDtoResponse(any(Reserva.class))).thenReturn(dtoResponse1);

        ReservaDtoResponse respuesta = servicio.saveReservaAdmin(dtoRequest);

        assertNotNull(respuesta);
        assertEquals(dtoResponse1.idReserva(), respuesta.idReserva());
        assertEquals(dtoResponse1.estadoReserva(), respuesta.estadoReserva());

        verify(historialReservaService).registrarCambioReserva(any(Reserva.class), eq(EstadoReserva.PENDIENTE), eq("RECREACIÓN"));

    }

    @Test
    void updateReservaAdmin_deberiaActualizarReservaCorrectamente() {
        Long idReserva = 1L;
        ReservaUpdateDtoRequest dto = new ReservaUpdateDtoRequest(
                horarioEspacio2.getIdHorarioEspacio(),
                LocalDate.of(2025, 5, 28),
                "NUEVO MOTIVO"
        );

        when(repository.findById(idReserva)).thenReturn(Optional.of(reserva1));
        when(horarioEspacioRepository.findById(dto.idHorarioEspacio())).thenReturn(Optional.of(horarioEspacio2));
        when(repository.findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(dto.fecha(), dto.idHorarioEspacio()))
                .thenReturn(Optional.empty());
        when(repository.save(any(Reserva.class))).thenReturn(reserva1);
        when(mapper.toReservaDtoResponse(any(Reserva.class))).thenReturn(dtoResponse1);

        ReservaDtoResponse result = servicio.updateReservaAdmin(idReserva, dto);

        assertNotNull(result);
        assertEquals(dtoResponse1, result);

        verify(repository).findById(idReserva);
        verify(horarioEspacioRepository).findById(dto.idHorarioEspacio());
        verify(repository).findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(dto.fecha(), dto.idHorarioEspacio());
        verify(repository).save(any(Reserva.class));
        verify(historialReservaService).registrarCambioReserva(reserva1, EstadoReserva.PENDIENTE, "NUEVO MOTIVO");
        verify(mapper).toReservaDtoResponse(reserva1);
    }


    @Test
    void saveReservaEstudiante() {

        ReservaEstDtoRequest dtoRequest = new ReservaEstDtoRequest(
                1L,
                LocalDate.of(2025, 6, 10),
                "RECREACIÓN"
        );

        Reserva reservaMapeada = Reserva.builder()
                .motivo("RECREACIÓN")
                .fecha(dtoRequest.fecha())
                .build();

        Reserva reservaGuardada = Reserva.builder()
                .idReserva(3L)
                .motivo("RECREACIÓN")
                .fecha(dtoRequest.fecha())
                .estadoReserva(EstadoReserva.PENDIENTE)
                .estudiante(estudiante1)
                .horarioEspacio(horarioEspacio1)
                .build();

        ReservaEstDtoResponse dtoResponseEsperado = new ReservaEstDtoResponse(
                3L,
                1L,
                dtoRequest.fecha(),
                "RECREACIÓN"
        );

        // Mocks
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante1));
        when(horarioEspacioRepository.findById(1L)).thenReturn(Optional.of(horarioEspacio1));
        when(repository.findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(dtoRequest.fecha(), dtoRequest.idHorarioEspacio()))
                .thenReturn(Optional.empty());

        when(mapper.toEntityEst(dtoRequest)).thenReturn(reservaMapeada);
        when(repository.save(any(Reserva.class))).thenReturn(reservaGuardada);
        when(mapper.toReservaEstDtoResponse(reservaGuardada)).thenReturn(dtoResponseEsperado);

        ReservaEstDtoResponse resultado = servicio.saveReservaEstudiante(dtoRequest, 1L);

        assertNotNull(resultado);
        assertEquals(dtoResponseEsperado, resultado);

        verify(estudianteRepository).findById(1L);
        verify(horarioEspacioRepository).findById(1L);
        verify(repository).findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(dtoRequest.fecha(), dtoRequest.idHorarioEspacio());
        verify(mapper).toEntityEst(dtoRequest);
        verify(repository).save(any(Reserva.class));
        verify(mapper).toReservaEstDtoResponse(reservaGuardada);
        verify(historialReservaService).registrarCambioReserva(reservaGuardada, EstadoReserva.PENDIENTE, "RECREACIÓN");

    }

    @Test
    void updateReservaEstudiante_deberiaActualizarReservaCorrectamente() {
        Long idReserva = 2L;
        ReservaEstDtoRequest dto = new ReservaEstDtoRequest(
                horarioEspacio1.getIdHorarioEspacio(),
                LocalDate.of(2025, 6, 3),
                "NUEVO MOTIVO ESTUDIANTE"
        );

        when(repository.findById(idReserva)).thenReturn(Optional.of(reserva2));
        when(horarioEspacioRepository.findById(dto.idHorarioEspacio())).thenReturn(Optional.of(horarioEspacio1));
        when(repository.findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(dto.fecha(), dto.idHorarioEspacio()))
                .thenReturn(Optional.empty());
        when(repository.save(any(Reserva.class))).thenReturn(reserva2);

        ReservaEstDtoResponse expectedResponse = new ReservaEstDtoResponse(
                reserva2.getIdReserva(),
                dto.idHorarioEspacio(),
                dto.fecha(),
                dto.motivo()
        );

        when(mapper.toReservaEstDtoResponse(any(Reserva.class))).thenReturn(expectedResponse);

        ReservaEstDtoResponse result = servicio.updateReservaEstudiante(idReserva, dto);

        assertNotNull(result);
        assertEquals(expectedResponse, result);

        verify(repository).findById(idReserva);
        verify(horarioEspacioRepository).findById(dto.idHorarioEspacio());
        verify(repository).findReservaByFechaAndHorarioEspacio_IdHorarioEspacio(dto.fecha(), dto.idHorarioEspacio());
        verify(repository).save(any(Reserva.class));
        verify(historialReservaService).registrarCambioReserva(reserva2, EstadoReserva.PENDIENTE, "NUEVO MOTIVO ESTUDIANTE");
        verify(mapper).toReservaEstDtoResponse(reserva2);
    }


    @Test
    void cancelarReserva() {

        ReservaCambioEstadoDtoRequest dto = new ReservaCambioEstadoDtoRequest(
                2L,
                "No podré asistir"
        );

        when(repository.findById(dto.idReserva())).thenReturn(Optional.of(reserva2));

        ReservaDtoResponse expectedResponse = new ReservaDtoResponse(
                reserva2.getIdReserva(),
                reserva2.getEstudiante().getIdEstudiante(),
                reserva2.getHorarioEspacio().getIdHorarioEspacio(),
                EstadoReserva.CANCELADA,
                reserva2.getFecha(),
                dto.motivo()
        );

        when(repository.save(any(Reserva.class))).thenReturn(reserva2);
        when(mapper.toReservaDtoResponse(any(Reserva.class))).thenReturn(expectedResponse);

        ReservaDtoResponse result = servicio.cancelarReserva(dto, dto.idReserva());

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        assertEquals(EstadoReserva.CANCELADA, result.estadoReserva());

        verify(repository).findById(dto.idReserva());
        verify(repository).save(any(Reserva.class));
        verify(historialReservaService).registrarCambioReserva(reserva2, EstadoReserva.CANCELADA, dto.motivo());
        verify(mapper).toReservaDtoResponse(reserva2);
    }

    @Test
    void aprobarReserva() {
        Long idReserva = 1L;

        when(repository.findById(idReserva)).thenReturn(Optional.of(reserva1));

        ReservaDtoResponse expectedResponse = new ReservaDtoResponse(
                reserva1.getIdReserva(),
                reserva1.getEstudiante().getIdEstudiante(),
                reserva1.getHorarioEspacio().getIdHorarioEspacio(),
                EstadoReserva.APROBADO,
                reserva1.getFecha(),
                reserva1.getMotivo()
        );

        when(repository.save(any(Reserva.class))).thenReturn(reserva1);
        when(mapper.toReservaDtoResponse(any(Reserva.class))).thenReturn(expectedResponse);

        ReservaDtoResponse result = servicio.aprobarReserva(idReserva);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        assertEquals(EstadoReserva.APROBADO, result.estadoReserva());

        verify(repository).findById(idReserva);
        verify(repository).save(any(Reserva.class));
        verify(historialReservaService).registrarCambioReserva(reserva1, EstadoReserva.APROBADO, "Reserva aprobada");
        verify(mapper).toReservaDtoResponse(reserva1);
    }

    @Test
    void rechazarReserva() {

        ReservaCambioEstadoDtoRequest dto = new ReservaCambioEstadoDtoRequest(
                3L,
                "Solicitud inválida"
        );

        Reserva reservaRechazar = Reserva.builder()
                .idReserva(3L)
                .estadoReserva(EstadoReserva.PENDIENTE)
                .estudiante(estudiante1)
                .horarioEspacio(horarioEspacio1)
                .fecha(LocalDate.of(2025, 6, 3))
                .motivo("JUEGO")
                .build();

        when(repository.findById(dto.idReserva())).thenReturn(Optional.of(reservaRechazar));

        ReservaDtoResponse expectedResponse = new ReservaDtoResponse(
                reservaRechazar.getIdReserva(),
                reservaRechazar.getEstudiante().getIdEstudiante(),
                reservaRechazar.getHorarioEspacio().getIdHorarioEspacio(),
                EstadoReserva.RECHAZADO,
                reservaRechazar.getFecha(),
                dto.motivo()
        );

        when(repository.save(any(Reserva.class))).thenReturn(reservaRechazar);
        when(mapper.toReservaDtoResponse(any(Reserva.class))).thenReturn(expectedResponse);

        ReservaDtoResponse result = servicio.rechazarReserva(dto.idReserva(), dto);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        assertEquals(EstadoReserva.RECHAZADO, result.estadoReserva());

        verify(repository).findById(dto.idReserva());
        verify(repository).save(any(Reserva.class));
        verify(historialReservaService).registrarCambioReserva(reservaRechazar, EstadoReserva.RECHAZADO, dto.motivo());
        verify(mapper).toReservaDtoResponse(reservaRechazar);
    }
}