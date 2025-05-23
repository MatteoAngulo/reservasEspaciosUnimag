package edu.unimagdalena.reservasespacios.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.unimagdalena.reservasespacios.dtos.mappers.EspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EspacioServiceImplTest {

    @Mock
    private EspacioRepository repositorio;

    @Mock
    private EspacioMapper mapper;

    @InjectMocks
    private EspacioServiceImpl espacioService;

    public EspacioServiceImplTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void CrearEspacio(){
        EspacioDTOResquests espacioDTO = EspacioDTOResquests.builder()
                .tipo("cancha")
                .nombre("cancha 1")
                .restricciones("no se juega después de 8").build();

        Espacio espacioMock = Espacio.builder()
                .idEspacio(1L)
                .tipo("cancha")
                .nombre("cancha 1")
                .restricciones("no se juega después de 8").build();

        EspacioDTOResponse expectedResponse = EspacioDTOResponse.builder()
                .id(1L)
                .tipo("cancha")
                .nombre("cancha 1")
                .restricciones("no se juega después de 8").build();

        when(mapper.dtoToEspacio(espacioDTO)).thenReturn(espacioMock);
        when(repositorio.save(espacioMock)).thenReturn(espacioMock);
        when(mapper.espacioToDTO(espacioMock)).thenReturn(expectedResponse);

        EspacioDTOResponse dtoResponse = espacioService.crearEspacio(espacioDTO);

        assertNotNull(dtoResponse);
        assertThat(dtoResponse.getId()).isNotNull();
        assertThat(dtoResponse.getTipo()).isEqualTo(espacioDTO.getTipo());
        assertThat(dtoResponse.getNombre()).isEqualTo(espacioDTO.getNombre());
        assertThat(dtoResponse.getRestricciones()).isEqualTo(espacioDTO.getRestricciones());

        verify(mapper).dtoToEspacio(espacioDTO);
        verify(repositorio, times(1)).save(espacioMock);
        verify(mapper).espacioToDTO(espacioMock);
    }

}