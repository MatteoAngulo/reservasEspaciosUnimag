package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ProblemaNotFoundException;
import edu.unimagdalena.reservasespacios.services.interfaces.ProblemaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProblemaEstudianteControllerTest {

    @Mock
    private ProblemaService problemaService;

    @InjectMocks
    private ProblemaEstudianteController controller;

    private Long idEst;
    private ProblemaDtoResponse response;
    private Date fecha;
    private ProblemaDtoRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        idEst = 5L;
        fecha = Date.valueOf("2025-05-20");
        response = new ProblemaDtoResponse(2L, 1L, "PENDIENTE", "Descripción test", fecha, idEst);
        request = new ProblemaDtoRequest(1L, "PENDIENTE", "Descripción test", fecha);
    }

    @Test
    void getById_devuelveOkYDto() {
        // Dado: existe un problema con ID 2
        when(problemaService.findProblemaById(2L)).thenReturn(response);

        // Cuando: el estudiante solicita getById
        ResponseEntity<ProblemaDtoResponse> res = controller.getById(idEst, 2L);

        // Entonces: recibe 200 OK con el DTO
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(response, res.getBody());
        verify(problemaService).findProblemaById(2L);
    }

    @Test
    void getById_noExiste_lanzaExcepcion() {
        // Dado: no existe problema 99
        when(problemaService.findProblemaById(99L))
                .thenThrow(new ProblemaNotFoundException("No existe"));

        // Cuando/Entonces: solicitar ID inexistente arroja excepción
        ProblemaNotFoundException ex = assertThrows(
                ProblemaNotFoundException.class,
                () -> controller.getById(idEst, 99L)
        );
        assertEquals("No existe", ex.getMessage());
    }

    @Test
    void getAll_devuelveOkYLista() {
        // Dado: el servicio devuelve lista de problemas del estudiante
        List<ProblemaDtoResponse> lista = List.of(response);
        when(problemaService.findProblemasPorEstudiante(idEst)).thenReturn(lista);

        // Cuando: se llama getAll
        ResponseEntity<List<ProblemaDtoResponse>> res = controller.getAll(idEst);

        // Entonces: recibe 200 OK con la lista
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(lista, res.getBody());
        verify(problemaService).findProblemasPorEstudiante(idEst);
    }

    @Test
    void getByEstado_devuelveFiltrado() {
        // Dado: servicio retorna 2 problemas uno con idEst y otro distinto
        ProblemaDtoResponse other = new ProblemaDtoResponse(3L, 1L, "PENDIENTE", "Desc", fecha, 99L);
        List<ProblemaDtoResponse> all = List.of(response, other);
        when(problemaService.findProblemasPorEstado("PENDIENTE")).thenReturn(all);

        // Cuando: se filtra por estado
        ResponseEntity<List<ProblemaDtoResponse>> res = controller.getByEstado(idEst, "PENDIENTE");

        // Entonces: solo aparece el del estudiante
        List<ProblemaDtoResponse> filtered = res.getBody();
        assertEquals(1, filtered.size());
        assertEquals(response, filtered.get(0));
        verify(problemaService).findProblemasPorEstado("PENDIENTE");
    }

    @Test
    void getByEspacio_devuelveFiltrado() {
        // Dado: servicio retorna 2 problemas uno perteneciente al estudiante
        ProblemaDtoResponse other = new ProblemaDtoResponse(3L, 1L, "PENDIENTE", "Desc", fecha, 99L);
        List<ProblemaDtoResponse> all = List.of(response, other);
        when(problemaService.findProblemasPorEspacio(1L)).thenReturn(all);

        // Cuando: se filtra por espacio
        ResponseEntity<List<ProblemaDtoResponse>> res = controller.getByEspacio(idEst, 1L);

        // Entonces: solo aparece el del estudiante
        List<ProblemaDtoResponse> filtered = res.getBody();
        assertEquals(1, filtered.size());
        assertEquals(response, filtered.get(0));
        verify(problemaService).findProblemasPorEspacio(1L);
    }

    @Test
    void create_devuelveCreatedYDto() {
        // Dado: el servicio guarda y retorna DTO
        when(problemaService.saveProblema(idEst, request)).thenReturn(response);

        // Cuando: el estudiante crea un problema
        ResponseEntity<ProblemaDtoResponse> res = controller.create(idEst, request);

        // Entonces: recibe 201 Created con el DTO
        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertEquals(response, res.getBody());
        verify(problemaService).saveProblema(idEst, request);
    }

    @Test
    void delete_devuelveNoContent() {
        // Dado: el servicio elimina correctamente
        doNothing().when(problemaService).deleteProblemaEstudiante(idEst, 2L);

        // Cuando: el estudiante elimina su problema
        ResponseEntity<Void> res = controller.delete(idEst, 2L);

        // Entonces: recibe 204 No Content
        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
        assertNull(res.getBody());
        verify(problemaService).deleteProblemaEstudiante(idEst, 2L);
    }
}
