package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.HorarioEspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import edu.unimagdalena.reservasespacios.enums.TipoEspacio;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.HorarioEspacioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorarioEspacioServiceImplTest {

    @Mock
    HorarioEspacioRepository repository;

    @Mock
    HorarioEspacioMapper mapper;

    @Mock
    EspacioRepository espacioRepository;

    @InjectMocks
    HorarioEspacioServiceImpl servicio;

    private Espacio espacio1;
    private Espacio espacio2;
    private HorarioEspacio horarioEspacio1;
    private HorarioEspacio horarioEspacio2;
    private HorarioEspacioDtoResponse dtoResponse1;
    private HorarioEspacioDtoResponse dtoResponse2;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);

        espacio1 = Espacio.builder()
                .idEspacio(1L)
                .nombre("CANCHA 1")
                .tipo(TipoEspacio.CANCHAS)
                .build();

        espacio2 = Espacio.builder()
                .idEspacio(2L)
                .nombre("AUDITORIO 1")
                .tipo(TipoEspacio.AUDITORIOS)
                .build();

        horarioEspacio1 = HorarioEspacio.builder()
                .idHorarioEspacio(1L)
                .espacio(espacio1)
                .dia(DayOfWeek.FRIDAY)
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(10, 0))
                .build();

        horarioEspacio2 = HorarioEspacio.builder()
                .idHorarioEspacio(2L)
                .espacio(espacio2)
                .dia(DayOfWeek.MONDAY)
                .horaInicio(LocalTime.of(10, 0))
                .horaFin(LocalTime.of(12, 0))
                .build();

        dtoResponse1 = new HorarioEspacioDtoResponse(
                1L,
                1L,
                DayOfWeek.FRIDAY,
                LocalTime.of(8, 0),
                LocalTime.of(10, 0)
        );

        dtoResponse2 = new HorarioEspacioDtoResponse(
                2L,
                2L,
                DayOfWeek.MONDAY,
                LocalTime.of(10, 0),
                LocalTime.of(12, 0)
        );

    }


    @Test
    void findHorarioEspacioById() {

        when(repository.findById(1L)).thenReturn(Optional.of(horarioEspacio1));
        when(mapper.toDtoResponse(horarioEspacio1)).thenReturn(dtoResponse1);

        HorarioEspacioDtoResponse result = servicio.findHorarioEspacioById(1L);

        assertNotNull(result);
        assertEquals(dtoResponse1.idHorarioEspacio(), result.idHorarioEspacio());
        assertEquals(dtoResponse1.horaInicio(), result.horaInicio());

        verify(repository, times(1)).findById(1L);

    }

    @Test
    void findAllHorarioEspacio() {

        List<HorarioEspacio> listaEntidades = List.of(horarioEspacio1, horarioEspacio2);
        List<HorarioEspacioDtoResponse> listaDtos = List.of(dtoResponse1, dtoResponse2);

        when(repository.findAll()).thenReturn(listaEntidades);

        when(mapper.toDtoResponse(horarioEspacio1)).thenReturn(dtoResponse1);
        when(mapper.toDtoResponse(horarioEspacio2)).thenReturn(dtoResponse2);

        List<HorarioEspacioDtoResponse> resultado = servicio.findAllHorarioEspacio();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(dtoResponse1, resultado.get(0));
        assertEquals(dtoResponse2, resultado.get(1));

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDtoResponse(horarioEspacio1);
        verify(mapper, times(1)).toDtoResponse(horarioEspacio2);

    }

    @Test
    void findHorariosPorDia() {
        when(repository.findHorarioEspacioByDia(DayOfWeek.FRIDAY)).thenReturn(List.of(horarioEspacio1));
        when(mapper.toDtoResponse(horarioEspacio1)).thenReturn(dtoResponse1);

        List<HorarioEspacioDtoResponse> resultado = servicio.findHorariosPorDia(DayOfWeek.FRIDAY);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(espacio1.getIdEspacio(), resultado.get(0).idHorarioEspacio());

    }

    @Test
    void findHorariosPorEspacio() {
        when(repository.findHorarioEspacioByEspacio_IdEspacio(2L)).thenReturn(List.of(horarioEspacio2));
        when(mapper.toDtoResponse(horarioEspacio2)).thenReturn(dtoResponse2);

        List<HorarioEspacioDtoResponse> resultado = servicio.findHorariosPorEspacio(2L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(DayOfWeek.MONDAY, resultado.get(0).dia());
    }

    @Test
    void findHorarioPorEspacioYDia() {
        when(repository.findHorarioEspaciosByDiaAndEspacio_IdEspacio(DayOfWeek.FRIDAY, 1L)).thenReturn(List.of(horarioEspacio1));
        when(mapper.toDtoResponse(horarioEspacio1)).thenReturn(dtoResponse1);

        List<HorarioEspacioDtoResponse> resultado = servicio.findHorarioPorEspacioYDia(DayOfWeek.FRIDAY, 1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.get(0).idEspacio());
        assertEquals(1, resultado.size());
    }

    @Test
    void saveHorarioEspacio() {

        HorarioEspacioDtoRequest dtoRequest = new HorarioEspacioDtoRequest(
                1L,
                DayOfWeek.FRIDAY,
                LocalTime.of(8, 0),
                LocalTime.of(10, 0)
        );

        when(mapper.toEntity(dtoRequest)).thenReturn(horarioEspacio1);
        when(espacioRepository.findById(1L)).thenReturn(Optional.of(espacio1));
        when(repository.save(horarioEspacio1)).thenReturn(horarioEspacio1);
        when(mapper.toDtoResponse(horarioEspacio1)).thenReturn(dtoResponse1);

        HorarioEspacioDtoResponse resultado = servicio.saveHorarioEspacio(dtoRequest);

        assertNotNull(resultado);
        assertEquals(dtoResponse1.idHorarioEspacio(), resultado.idHorarioEspacio());
        assertEquals(dtoResponse1.idEspacio(), resultado.idEspacio());
        assertEquals(dtoResponse1.dia(), resultado.dia());

        verify(mapper).toEntity(dtoRequest);
        verify(espacioRepository).findById(1L);
        verify(repository).save(horarioEspacio1);
        verify(mapper).toDtoResponse(horarioEspacio1);

    }

    @Test
    void updateHorarioEspacio() {

        Long id = 1L;

        HorarioEspacioDtoRequest dtoRequest = new HorarioEspacioDtoRequest(
                1L,
                DayOfWeek.FRIDAY,
                LocalTime.of(8, 0),
                LocalTime.of(10, 0)
        );

        when(repository.findById(id)).thenReturn(Optional.of(horarioEspacio1));
        doNothing().when(mapper).updateHorarioEspacioFromDto(dtoRequest, horarioEspacio1);
        when(repository.save(horarioEspacio1)).thenReturn(horarioEspacio1);
        when(mapper.toDtoResponse(horarioEspacio1)).thenReturn(dtoResponse1);

        HorarioEspacioDtoResponse resultado = servicio.updateHorarioEspacio(id, dtoRequest);

        assertNotNull(resultado);
        assertEquals(dtoResponse1.idHorarioEspacio(), resultado.idHorarioEspacio());
        assertEquals(dtoResponse1.dia(), resultado.dia());

        verify(repository).findById(id);
        verify(mapper).updateHorarioEspacioFromDto(dtoRequest, horarioEspacio1);
        verify(repository).save(horarioEspacio1);
        verify(mapper).toDtoResponse(horarioEspacio1);
    }

    @Test
    void deleteHorarioEspacio() {

        when(repository.existsById(1L)).thenReturn(true);

        servicio.deleteHorarioEspacio(1L);

        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}