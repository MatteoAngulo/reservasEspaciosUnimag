package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.HorarioEspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import edu.unimagdalena.reservasespacios.enums.TipoEspacio;
import edu.unimagdalena.reservasespacios.repositories.HorarioEspacioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorarioEspacioServiceImplTest {

    @Mock
    HorarioEspacioRepository repository;

    @Mock
    HorarioEspacioMapper mapper;

    @InjectMocks
    HorarioEspacioServiceImpl servicio;

    private Espacio espacio;
    private HorarioEspacio horarioEspacio;
    private HorarioEspacioDtoResponse dtoResponse;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);

        espacio = Espacio.builder()
                .idEspacio(1L)
                .nombre("CANCHA 1")
                .tipo(TipoEspacio.CANCHAS)
                .build();

        horarioEspacio = HorarioEspacio.builder()
                .idHorarioEspacio(1L)
                .espacio(espacio)
                .dia(DayOfWeek.FRIDAY)
                .horaInicio(LocalTime.of(8, 0))
                .horaFin(LocalTime.of(10, 0))
                .build();

        dtoResponse = new HorarioEspacioDtoResponse(
                1L,
                1L,
                DayOfWeek.FRIDAY,
                LocalTime.of(8, 0),
                LocalTime.of(10, 0)
        );

    }


    @Test
    void findHorarioEspacioById() {

        when(repository.findById(1L)).thenReturn(Optional.of(horarioEspacio));
        when(mapper.toDtoResponse(horarioEspacio)).thenReturn(dtoResponse);

        HorarioEspacioDtoResponse result = servicio.findHorarioEspacioById(1L);

        assertNotNull(result);
        assertEquals(dtoResponse.idHorarioEspacio(), result.idHorarioEspacio());
        assertEquals(dtoResponse.horaInicio(), result.horaInicio());

        verify(repository, times(1)).findById(1L);

    }

    @Test
    void findAllHorarioEspacio() {
    }

    @Test
    void findHorariosPorDia() {
    }

    @Test
    void findHorariosPorEspacio() {
    }

    @Test
    void findHorarioPorEspacioYDia() {
    }

    @Test
    void saveHorarioEspacio() {
    }

    @Test
    void updateHorarioEspacio() {
    }

    @Test
    void deleteHorarioEspacio() {
    }
}