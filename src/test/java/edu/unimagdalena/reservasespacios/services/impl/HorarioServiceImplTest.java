package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioDtoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HorarioServiceImplTest {

    @Mock HorarioRepository horarioRepository;

    @Mock HorarioMapper horarioMapper;

    @InjectMocks
    HorarioServiceImpl horarioService;

    @Test
    void findById() {

        Long id = 1L;
        Horario horario = Horario.builder()
                .idHorario(id)
                .horaInicio(LocalTime.of(2, 0))
                .horaFin(LocalTime.of(4, 0))
                .build();

        HorarioDtoRequest horarioDTO = new HorarioDtoRequest(LocalTime.of(2, 0),
                LocalTime.of(4, 0));


        when(horarioMapper.toEntity(horarioDTO)).thenReturn(horario);
        when(horarioRepository);
    }

    @Test
    void findAll() {
    }

    @Test
    void saveHorario() {
    }

    @Test
    void updateHorario() {
    }

    @Test
    void deleteHorario() {
    }
}