package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Sede;
import edu.unimagdalena.reservasespacios.enums.TipoEspacio;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.SedeNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.SedeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EspacioServiceImplTest {

    @Mock
    private EspacioRepository repository;

    @Mock
    private SedeRepository sedeRepository;

    @Mock
    private EspacioMapper mapper;

    @InjectMocks
    private EspacioServiceImpl service;

    private EspacioDTOResquests dto;
    private Espacio entidad;
    private EspacioDTOResponse dtoResponse;
    private Sede sede;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dto = new EspacioDTOResquests("Sala", "SALONES", "Sin obs", 1L, true);
        entidad = Espacio.builder()
                .idEspacio(2L)
                .nombre("Sala")
                .tipo(TipoEspacio.SALONES)
                .restricciones("Sin obs")
                .disponible(true)
                .build();
        dtoResponse = new EspacioDTOResponse(2L, "Sala", "SALONES", "Sin obs", 1L, true);
        sede = Sede.builder().sedeId(1L).name("Sede A").build();
    }

    @Test
    void testCrearEspacio_exitoso() {
        when(mapper.dtoToEspacio(dto)).thenReturn(entidad);
        when(sedeRepository.findById(1L)).thenReturn(Optional.of(sede));
        Espacio saved = Espacio.builder()
                .idEspacio(2L)
                .nombre("Sala")
                .tipo(TipoEspacio.SALONES)
                .restricciones("Sin obs")
                .disponible(true)
                .sede(sede)
                .build();
        when(repository.save(entidad)).thenReturn(saved);
        when(mapper.espacioToDTO(saved)).thenReturn(dtoResponse);

        EspacioDTOResponse result = service.crearEspacio(dto);

        assertEquals(dtoResponse, result);
        verify(repository).save(entidad);
    }

    @Test
    void testCrearEspacio_sedeNoExiste_lanzaExcepcion() {
        when(mapper.dtoToEspacio(dto)).thenReturn(entidad);
        when(sedeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SedeNotFoundException.class, () -> service.crearEspacio(dto));
    }

    @Test
    void testFindAllEspacios() {
        Espacio e1 = entidad;
        Espacio e2 = Espacio.builder().idEspacio(3L).nombre("Aula").tipo(TipoEspacio.SALONES).disponible(false).build();
        EspacioDTOResponse r1 = dtoResponse;
        EspacioDTOResponse r2 = new EspacioDTOResponse(3L, "Aula", "SALONES", null, null, false);
        when(repository.findAll()).thenReturn(Arrays.asList(e1, e2));
        when(mapper.espacioToDTO(e1)).thenReturn(r1);
        when(mapper.espacioToDTO(e2)).thenReturn(r2);

        List<EspacioDTOResponse> list = service.findAllEspacios();

        assertEquals(2, list.size());
        assertEquals(r1, list.get(0));
        assertEquals(r2, list.get(1));
    }

    @Test
    void testFindEspacioById_existe() {
        when(repository.findById(2L)).thenReturn(Optional.of(entidad));
        when(mapper.espacioToDTO(entidad)).thenReturn(dtoResponse);

        EspacioDTOResponse res = service.findEspacioById(2L);

        assertEquals(dtoResponse, res);
    }

    @Test
    void testFindEspacioById_noExiste() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EspacioNotFoundException.class, () -> service.findEspacioById(5L));
    }

    @Test
    void testUpdateEspacio_exitoso() {
        // existente sin sede
        Espacio existente = entidad;
        when(repository.findById(2L)).thenReturn(Optional.of(existente));
        doAnswer(invocation -> {
            // simula mapper update
            existente.setNombre(dto.nombre());
            existente.setTipo(TipoEspacio.valueOf(dto.tipo()));
            existente.setRestricciones(dto.restricciones());
            existente.setDisponible(dto.disponible());
            return null;
        }).when(mapper).updateEspacioFromRequestDTO(dto, existente);
        when(sedeRepository.findById(1L)).thenReturn(Optional.of(sede));
        when(repository.save(existente)).thenReturn(entidad);
        when(mapper.espacioToDTO(entidad)).thenReturn(dtoResponse);

        EspacioDTOResponse res = service.updateEspacio(2L, dto);

        assertEquals(dtoResponse, res);
        verify(repository).save(existente);
    }

    @Test
    void testUpdateEspacio_noExiste_lanzaExcepcion() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EspacioNotFoundException.class, () -> service.updateEspacio(2L, dto));
    }

    @Test
    void testDeleteEspacio_exitoso() {
        when(repository.existsById(2L)).thenReturn(true);

        service.deleteEspacio(2L);

        verify(repository).deleteById(2L);
    }

    @Test
    void testDeleteEspacio_noExiste_lanzaExcepcion() {
        when(repository.existsById(anyLong())).thenReturn(false);

        assertThrows(EspacioNotFoundException.class, () -> service.deleteEspacio(9L));
    }

    @Test
    void testFindEspaciosBySede_exitoso() {
        Espacio e1 = entidad;
        EspacioDTOResponse r1 = dtoResponse;
        when(sedeRepository.findById(1L)).thenReturn(Optional.of(sede));
        when(repository.findBySede_SedeId(1L)).thenReturn(List.of(e1));
        when(mapper.espacioToDTO(e1)).thenReturn(r1);

        List<EspacioDTOResponse> list = service.findEspaciosBySede(1L);

        assertEquals(1, list.size());
        assertEquals(r1, list.get(0));
    }

    @Test
    void testFindEspaciosBySede_sedeNoExiste_lanzaExcepcion() {
        when(sedeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SedeNotFoundException.class, () -> service.findEspaciosBySede(1L));
    }
}
