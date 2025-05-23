package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.SedeMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Sede;
import edu.unimagdalena.reservasespacios.exceptions.notFound.SedeNotFoundException;
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
import static org.mockito.Mockito.*;

class SedeServiceImplTest {

    @Mock
    private SedeRepository sedeRepository;

    @Mock
    private SedeMapper sedeMapper;

    @InjectMocks
    private SedeServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSedes() {
        // Dado
        Sede s1 = Sede.builder().sedeId(1L).name("A").build();
        Sede s2 = Sede.builder().sedeId(2L).name("B").build();
        when(sedeRepository.findAll()).thenReturn(Arrays.asList(s1, s2));
        when(sedeMapper.sedeToDto(s1)).thenReturn(new SedeDtoResponse(1L, "A"));
        when(sedeMapper.sedeToDto(s2)).thenReturn(new SedeDtoResponse(2L, "B"));

        // Cuando
        List<SedeDtoResponse> result = service.findAllSedes();

        // Entonces
        assertEquals(2, result.size(), "Debe devolver dos sedes");
        assertEquals("A", result.get(0).name());
        assertEquals("B", result.get(1).name());
        verify(sedeRepository).findAll();
    }

    @Test
    void testFindSedeByIdExists() {
        // Dado
        Sede sede = Sede.builder().sedeId(5L).name("X").build();
        when(sedeRepository.findById(5L)).thenReturn(Optional.of(sede));
        when(sedeMapper.sedeToDto(sede)).thenReturn(new SedeDtoResponse(5L, "X"));

        // Cuando
        SedeDtoResponse dto = service.findSedeById(5L);

        // Entonces
        assertNotNull(dto, "DTO no debe ser nulo");
        assertEquals(5L, dto.id());
        assertEquals("X", dto.name());
    }

    @Test
    void testFindSedeByIdNotFound() {
        when(sedeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SedeNotFoundException.class,
                () -> service.findSedeById(99L),
                "Debe lanzar excepción cuando no existe la sede");
    }

    @Test
    void testSaveSede() {
        // Dado
        SedeDtoRequest request = new SedeDtoRequest("Nueva");
        Sede entidad = Sede.builder().name("Nueva").build();
        Sede savedEntity = Sede.builder().sedeId(10L).name("Nueva").build();

        when(sedeMapper.dtoToSede(request)).thenReturn(entidad);
        when(sedeRepository.save(entidad)).thenReturn(savedEntity);
        when(sedeMapper.sedeToDto(savedEntity)).thenReturn(new SedeDtoResponse(10L, "Nueva"));

        // Cuando
        SedeDtoResponse dto = service.saveSede(request);

        // Entonces
        assertEquals(10L, dto.id());
        assertEquals("Nueva", dto.name());
        verify(sedeRepository).save(entidad);
    }

    @Test
    void testUpdateSedeExists() {
        // Dado
        SedeDtoRequest request = new SedeDtoRequest("Upd");
        Sede existente = Sede.builder().sedeId(7L).name("Old").build();
        Sede updatedEntity = Sede.builder().sedeId(7L).name("Upd").build();

        when(sedeRepository.findById(7L)).thenReturn(Optional.of(existente));
        doAnswer(invocation -> {
            // Simula comportamiento del mapper.update
            existente.setName(request.name());
            return null;
        }).when(sedeMapper).updateSedeFromDto(request, existente);
        when(sedeRepository.save(existente)).thenReturn(updatedEntity);
        when(sedeMapper.sedeToDto(updatedEntity)).thenReturn(new SedeDtoResponse(7L, "Upd"));

        // Cuando
        SedeDtoResponse dto = service.updateSede(7L, request);

        // Entonces
        assertEquals(7L, dto.id());
        assertEquals("Upd", dto.name());
        verify(sedeMapper).updateSedeFromDto(request, existente);
        verify(sedeRepository).save(existente);
    }

    @Test
    void testUpdateSedeNotFound() {
        when(sedeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SedeNotFoundException.class,
                () -> service.updateSede(5L, new SedeDtoRequest("N")),
                "Debe lanzar excepción cuando no existe la sede");
    }

    @Test
    void testDeleteSedeExists() {
        when(sedeRepository.existsById(3L)).thenReturn(true);

        // Cuando
        service.deleteSede(3L);

        // Entonces
        verify(sedeRepository).deleteById(3L);
    }

    @Test
    void testDeleteSedeNotFound() {
        when(sedeRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(SedeNotFoundException.class,
                () -> service.deleteSede(8L),
                "Debe lanzar excepción cuando no existe la sede");
    }
}
