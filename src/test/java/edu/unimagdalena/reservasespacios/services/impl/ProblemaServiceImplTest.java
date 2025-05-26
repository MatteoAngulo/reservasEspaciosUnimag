package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.mappers.ProblemaMapper;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.Problema;
import edu.unimagdalena.reservasespacios.enums.EstadoProblema;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EstudianteNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ProblemaNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
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

class ProblemaServiceImplTest {

    @Mock
    private ProblemaRepository problemaRepository;

    @Mock
    private EspacioRepository espacioRepository;

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private ProblemaMapper mapper;

    @InjectMocks
    private ProblemaServiceImpl service;

    private ProblemaDtoRequest dto;
    private Problema entity;
    private ProblemaDtoResponse response;
    private Espacio espacio;
    private Estudiante estudiante;

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
        response = new ProblemaDtoResponse(2L, 1L, "PENDIENTE", "Descripción test", fecha, 5L);
        espacio = Espacio.builder().idEspacio(1L).build();
        estudiante = new Estudiante(); estudiante.setIdEstudiante(5L);
    }

    @Test
    void findAllProblemas_devuelveLista() {
        // Dado
        Problema otro = Problema.builder().idProblema(3L).build();
        when(problemaRepository.findAll()).thenReturn(Arrays.asList(entity, otro));
        when(mapper.toResponse(entity)).thenReturn(response);
        when(mapper.toResponse(otro)).thenReturn(new ProblemaDtoResponse(3L, null, null, null, null, null));

        // Cuando
        List<ProblemaDtoResponse> lista = service.findAllProblemas();

        // Entonces
        assertEquals(2, lista.size());
        verify(mapper).toResponse(entity);
        verify(mapper).toResponse(otro);
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
                () -> service.findProblemaById(5L));
    }

    @Test
    void findProblemasPorEstudiante_devuelveLista() {
        // Dado
        when(problemaRepository.findByEstudiante_IdEstudiante(5L))
                .thenReturn(Arrays.asList(entity));
        when(mapper.toResponse(entity)).thenReturn(response);

        // Cuando
        List<ProblemaDtoResponse> lista = service.findProblemasPorEstudiante(5L);

        // Entonces
        assertEquals(1, lista.size());
        assertEquals(response, lista.get(0));
    }

    @Test
    void findProblemasPorEstado_devuelveLista() {
        // Dado
        Problema otro = Problema.builder().idProblema(3L).estado(EstadoProblema.PENDIENTE).build();
        when(problemaRepository.findByEstado(EstadoProblema.PENDIENTE))
                .thenReturn(Arrays.asList(entity, otro));
        when(mapper.toResponse(entity)).thenReturn(response);
        when(mapper.toResponse(otro)).thenReturn(new ProblemaDtoResponse(3L, null, "PENDIENTE", null, null, null));

        // Cuando
        List<ProblemaDtoResponse> lista = service.findProblemasPorEstado("PENDIENTE");

        // Entonces
        assertEquals(2, lista.size());
    }

    @Test
    void findProblemasPorEspacio_devuelveLista() {
        // Dado
        Problema otro = Problema.builder().idProblema(4L).build();
        when(problemaRepository.findByEspacio_IdEspacio(1L))
                .thenReturn(Arrays.asList(entity, otro));
        when(mapper.toResponse(entity)).thenReturn(response);
        when(mapper.toResponse(otro)).thenReturn(new ProblemaDtoResponse(4L, 1L, null, null, null, null));

        // Cuando
        List<ProblemaDtoResponse> lista = service.findProblemasPorEspacio(1L);

        // Entonces
        assertEquals(2, lista.size());
    }

    @Test
    void saveProblema_estudiante_exitoso() {
        // Dado
        when(estudianteRepository.findById(5L)).thenReturn(Optional.of(estudiante));
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(espacioRepository.findById(1L)).thenReturn(Optional.of(espacio));
        entity.setEstudiante(estudiante);
        when(problemaRepository.save(entity)).thenReturn(entity);
        when(mapper.toResponse(entity)).thenReturn(response);

        // Cuando
        ProblemaDtoResponse res = service.saveProblema(5L, dto);

        // Entonces
        assertEquals(response, res);
        verify(problemaRepository).save(entity);
    }

    @Test
    void saveProblema_estudianteNoExiste_lanzaExcepcion() {
        // Dado
        when(estudianteRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Cuando/Entonces
        assertThrows(EstudianteNotFoundException.class,
                () -> service.saveProblema(5L, dto));
    }

    @Test
    void deleteProblemaEstudiante_exitoso() {
        // Dado
        entity.setEstudiante(estudiante);
        when(problemaRepository.findById(2L)).thenReturn(Optional.of(entity));

        // Cuando
        service.deleteProblemaEstudiante(5L, 2L);

        // Entonces
        verify(problemaRepository).deleteById(2L);
    }

    @Test
    void deleteProblemaEstudiante_noExiste_lanzaExcepcion() {
        // Dado
        when(problemaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Cuando/Entonces
        assertThrows(ProblemaNotFoundException.class,
                () -> service.deleteProblemaEstudiante(5L, 2L));
    }

    @Test
    void deleteProblemaEstudiante_noPertenece_lanzaSecurityException() {
        // Dado
        Estudiante otro = new Estudiante(); otro.setIdEstudiante(9L);
        entity.setEstudiante(otro);
        when(problemaRepository.findById(2L)).thenReturn(Optional.of(entity));

        // Cuando/Entonces
        assertThrows(SecurityException.class,
                () -> service.deleteProblemaEstudiante(5L, 2L));
    }
}
