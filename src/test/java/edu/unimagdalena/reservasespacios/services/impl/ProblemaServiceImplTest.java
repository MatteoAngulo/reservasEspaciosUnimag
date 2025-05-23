package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.mappers.ProblemaMapper;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Problema;
import edu.unimagdalena.reservasespacios.enums.EstadoProblema;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ProblemaNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.ProblemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

// Pruebas unitarias para ProblemaServiceImpl
class ProblemaServiceImplTest {

    @Mock
    private ProblemaRepository problemaRepository;

    @Mock
    private EspacioRepository espacioRepository;

    @Mock
    private ProblemaMapper mapper;

    @InjectMocks
    private ProblemaServiceImpl service;

    private ProblemaDtoRequest dto;
    private Problema entity;
    private ProblemaDtoResponse response;
    private Espacio espacio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Date fecha = Date.valueOf("2025-05-20");
        dto = new ProblemaDtoRequest(1L, "PENDIENTE", "Descripción test", fecha);
        entity = Problema.builder()
                .idProblema(2L)
                .estado(EstadoProblema.PENDIENTE)
                .descripcion("Descripción test")
                .fecha(fecha)
                .build();
        response = new ProblemaDtoResponse(2L, 1L, "PENDIENTE", "Descripción test", fecha);
        espacio = Espacio.builder().idEspacio(1L).build();
    }

    @Test
    void findAllProblemas_devuelveLista() {
        // Dado
        Problema otro = Problema.builder().idProblema(3L).build();
        ProblemaDtoResponse otroResp = new ProblemaDtoResponse(3L, null, null, null, null);
        when(problemaRepository.findAll()).thenReturn(Arrays.asList(entity, otro));
        when(mapper.toResponse(entity)).thenReturn(response);
        when(mapper.toResponse(otro)).thenReturn(otroResp);

        // Cuando
        List<ProblemaDtoResponse> lista = service.findAllProblemas();

        // Entonces
        assertEquals(2, lista.size(), "Debe devolver dos elementos");
        assertEquals(response, lista.get(0));
        assertEquals(otroResp, lista.get(1));
    }

    @Test
    void findProblemaById_existe() {
        // Dado
        when(problemaRepository.findById(2L)).thenReturn(Optional.of(entity));
        when(mapper.toResponse(entity)).thenReturn(response);

        // Cuando
        ProblemaDtoResponse res = service.findProblemaById(2L);

        // Entonces
        assertEquals(response, res);
    }

    @Test
    void findProblemaById_noExiste_lanzaExcepcion() {
        // Dado
        when(problemaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Cuando/Entonces
        assertThrows(ProblemaNotFoundException.class,
                () -> service.findProblemaById(5L),
                "Debe lanzar ProblemaNotFoundException");
    }

    @Test
    void saveProblema_exitoso() {
        // Dado
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(espacioRepository.findById(1L)).thenReturn(Optional.of(espacio));
        Problema saved = entity;
        saved.setEspacio(espacio);
        when(problemaRepository.save(entity)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(response);

        // Cuando
        ProblemaDtoResponse res = service.saveProblema(dto);

        // Entonces
        assertEquals(response, res);
        verify(problemaRepository).save(entity);
    }

    @Test
    void saveProblema_espacioNoExiste_lanzaExcepcion() {
        // Dado
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(espacioRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Cuando/Entonces
        assertThrows(EspacioNotFoundException.class,
                () -> service.saveProblema(dto),
                "Debe lanzar EspacioNotFoundException");
    }

    @Test
    void updateProblema_exitoso() {
        // Dado
        Problema existente = entity;
        Date newDate = Date.valueOf("2025-05-21");
        ProblemaDtoRequest upd = new ProblemaDtoRequest(1L, "CERRADO", "Desc up", newDate);
        when(problemaRepository.findById(2L)).thenReturn(Optional.of(existente));
        doAnswer(invocation -> {
            // Simula mapper.updateFromDto
            existente.setEstado(EstadoProblema.CERRADO);
            existente.setDescripcion("Desc up");
            existente.setFecha(newDate);
            return null;
        }).when(mapper).updateFromDto(upd, existente);
        when(espacioRepository.findById(1L)).thenReturn(Optional.of(espacio));
        when(problemaRepository.save(existente)).thenReturn(existente);
        ProblemaDtoResponse updResp = new ProblemaDtoResponse(2L, 1L, "CERRADO", "Desc up", newDate);
        when(mapper.toResponse(existente)).thenReturn(updResp);

        // Cuando
        ProblemaDtoResponse res = service.updateProblema(2L, upd);

        // Entonces
        assertEquals(updResp, res);
        verify(problemaRepository).save(existente);
    }

    @Test
    void updateProblema_noExiste_lanzaExcepcion() {
        // Dado
        when(problemaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Cuando/Entonces
        assertThrows(ProblemaNotFoundException.class,
                () -> service.updateProblema(2L, dto),
                "Debe lanzar ProblemaNotFoundException");
    }

    @Test
    void deleteProblema_exitoso() {
        // Dado
        when(problemaRepository.existsById(2L)).thenReturn(true);

        // Cuando
        service.deleteProblema(2L);

        // Entonces
        verify(problemaRepository).deleteById(2L);
    }

    @Test
    void deleteProblema_noExiste_lanzaExcepcion() {
        // Dado
        when(problemaRepository.existsById(anyLong())).thenReturn(false);

        // Cuando/Entonces
        assertThrows(ProblemaNotFoundException.class,
                () -> service.deleteProblema(9L),
                "Debe lanzar ProblemaNotFoundException");
    }
}
