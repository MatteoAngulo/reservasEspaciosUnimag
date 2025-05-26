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

class ProblemaAdminControllerTest {

    @Mock
    private ProblemaService problemaService;

    @InjectMocks
    private ProblemaAdminController problemaAdminController;

    private ProblemaDtoResponse response;
    private Date fecha;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fecha = Date.valueOf("2025-05-20");
        response = new ProblemaDtoResponse(2L, 1L, "PENDIENTE", "Descripcion test", fecha, 5L);
    }

    @Test
    void listarTodos_devuelveOkYLista() {
        // Dado
        List<ProblemaDtoResponse> lista = List.of(response);
        when(problemaService.findAllProblemas()).thenReturn(lista);

        // Cuando
        ResponseEntity<List<ProblemaDtoResponse>> res = problemaAdminController.getAll();

        // Entonces
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(lista, res.getBody());
        verify(problemaService).findAllProblemas();
    }

    @Test
    void obtenerPorId_devuelveOkYDto() {
        // Dado
        when(problemaService.findProblemaById(2L)).thenReturn(response);

        // Cuando
        ResponseEntity<ProblemaDtoResponse> res = problemaAdminController.getById(2L);

        // Entonces
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(response, res.getBody());
        verify(problemaService).findProblemaById(2L);
    }

    @Test
    void obtenerPorId_noExiste_lanzaExcepcion() {
        // Dado
        when(problemaService.findProblemaById(99L))
                .thenThrow(new ProblemaNotFoundException("No existe"));

        // Cuando/Entonces
        ProblemaNotFoundException ex = assertThrows(
                ProblemaNotFoundException.class,
                () -> problemaAdminController.getById(99L)
        );
        assertEquals("No existe", ex.getMessage());
    }

    @Test
    void obtenerPorEstudiante_devuelveOkYLista() {
        // Dado
        long idEst = 5L;
        List<ProblemaDtoResponse> lista = List.of(response);
        when(problemaService.findProblemasPorEstudiante(idEst)).thenReturn(lista);

        // Cuando
        ResponseEntity<List<ProblemaDtoResponse>> res = problemaAdminController.getByEstudiante(idEst);

        // Entonces
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(lista, res.getBody());
        verify(problemaService).findProblemasPorEstudiante(idEst);
    }

    @Test
    void obtenerPorEstado_devuelveOkYLista() {
        // Dado
        String estado = "PENDIENTE";
        List<ProblemaDtoResponse> lista = List.of(response);
        when(problemaService.findProblemasPorEstado(estado)).thenReturn(lista);

        // Cuando
        ResponseEntity<List<ProblemaDtoResponse>> res = problemaAdminController.getByEstado(estado);

        // Entonces
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(lista, res.getBody());
        verify(problemaService).findProblemasPorEstado(estado);
    }

    @Test
    void obtenerPorEspacio_devuelveOkYLista() {
        // Dado
        long idEsp = 1L;
        List<ProblemaDtoResponse> lista = List.of(response);
        when(problemaService.findProblemasPorEspacio(idEsp)).thenReturn(lista);

        // Cuando
        ResponseEntity<List<ProblemaDtoResponse>> res = problemaAdminController.getByEspacio(idEsp);

        // Entonces
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(lista, res.getBody());
        verify(problemaService).findProblemasPorEspacio(idEsp);
    }

    @Test
    void actualizar_devuelveOkYDto() {
        // Dado
        ProblemaDtoRequest updReq = new ProblemaDtoRequest(1L, "CERRADO", "Actualizado", Date.valueOf("2025-05-21"));
        ProblemaDtoResponse updResp = new ProblemaDtoResponse(2L, 1L, "CERRADO", "Actualizado", Date.valueOf("2025-05-21"), 5L);
        when(problemaService.updateProblema(eq(2L), any(ProblemaDtoRequest.class))).thenReturn(updResp);

        // Cuando
        ResponseEntity<ProblemaDtoResponse> res = problemaAdminController.update(2L, updReq);

        // Entonces
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(updResp, res.getBody());
        verify(problemaService).updateProblema(2L, updReq);
    }

    @Test
    void eliminar_devuelveNoContent() {
        // Dado
        doNothing().when(problemaService).deleteProblema(2L);

        // Cuando
        ResponseEntity<Void> res = problemaAdminController.delete(2L);

        // Entonces
        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
        assertNull(res.getBody());
        verify(problemaService).deleteProblema(2L);
    }
}
