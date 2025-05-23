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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProblemaControllerTest {

    @Mock
    private ProblemaService problemaService;

    @InjectMocks
    private ProblemaController problemaController;

    private ProblemaDtoRequest request;
    private ProblemaDtoResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Date fecha = Date.valueOf("2025-05-20");
        request = new ProblemaDtoRequest(1L, "PENDIENTE", "Descripcion test", fecha);
        response = new ProblemaDtoResponse(2L, 1L, "PENDIENTE", "Descripcion test", fecha);
    }

    @Test
    void crearProblema_devuelveCreatedYDto() {
        // Dado: el servicio creará un problema y retornará un DTO
        when(problemaService.saveProblema(any(ProblemaDtoRequest.class))).thenReturn(response);

        // Cuando: se llama a crearProblema en el controlador
        ResponseEntity<ProblemaDtoResponse> res = problemaController.crearProblema(request);

        // Entonces: debe responder 201 Created con el DTO generado
        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertEquals(response, res.getBody());
        verify(problemaService).saveProblema(request);
    }

    @Test
    void listarProblemas_devuelveOkYLista() {
        // Dado: el servicio devuelve una lista con un problema
        List<ProblemaDtoResponse> lista = List.of(response);
        when(problemaService.findAllProblemas()).thenReturn(lista);

        // Cuando: se llama a listarProblemas
        ResponseEntity<List<ProblemaDtoResponse>> res = problemaController.listarProblemas();

        // Entonces: debe responder 200 OK con la lista completa
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(lista, res.getBody());
        verify(problemaService).findAllProblemas();
    }

    @Test
    void obtenerProblema_devuelveOkYDto() {
        // Dado: el servicio encuentra un problema con ID 2
        when(problemaService.findProblemaById(2L)).thenReturn(response);

        // Cuando: se llama a obtenerProblema con ID 2
        ResponseEntity<ProblemaDtoResponse> res = problemaController.obtenerProblema(2L);

        // Entonces: debe responder 200 OK con el DTO correspondiente
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(response, res.getBody());
        verify(problemaService).findProblemaById(2L);
    }

    @Test
    void obtenerProblema_lanzaExcepcion() {
        // Dado: el servicio lanza ProblemaNotFoundException para ID inexistente
        when(problemaService.findProblemaById(99L))
                .thenThrow(new ProblemaNotFoundException("No existe"));

        // Cuando/Entonces: llamar a obtenerProblema debe lanzar la excepción
        ProblemaNotFoundException ex = assertThrows(
                ProblemaNotFoundException.class,
                () -> problemaController.obtenerProblema(99L)
        );
        assertEquals("No existe", ex.getMessage());
    }

    @Test
    void actualizarProblema_devuelveOkYDto() {
        // Dado: un request de actualización y el servicio retorna el DTO actualizado
        ProblemaDtoRequest upd = new ProblemaDtoRequest(1L, "CERRADO", "Actualizado", Date.valueOf("2025-05-21"));
        ProblemaDtoResponse updResp = new ProblemaDtoResponse(2L, 1L, "CERRADO", "Actualizado", Date.valueOf("2025-05-21"));
        when(problemaService.updateProblema(eq(2L), any(ProblemaDtoRequest.class)))
                .thenReturn(updResp);

        // Cuando: se llama a actualizarProblema con ID 2
        ResponseEntity<ProblemaDtoResponse> res = problemaController.actualizarProblema(2L, upd);

        // Entonces: debe responder 200 OK con el DTO actualizado
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(updResp, res.getBody());
        verify(problemaService).updateProblema(2L, upd);
    }

    @Test
    void eliminarProblema_devuelveNoContent() {
        // Dado: el servicio elimina sin errores
        doNothing().when(problemaService).deleteProblema(2L);

        // Cuando: se llama a eliminarProblema con ID 2
        ResponseEntity<Void> res = problemaController.eliminarProblema(2L);

        // Entonces: debe responder 204 No Content y cuerpo nulo
        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
        assertNull(res.getBody());
        verify(problemaService).deleteProblema(2L);
    }

    @Test
    void eliminarProblema_lanzaExcepcion() {
        // Dado: el servicio lanza excepcion al eliminar ID inexistente
        doThrow(new ProblemaNotFoundException("No existe"))
                .when(problemaService).deleteProblema(5L);

        // Cuando/Entonces: eliminarProblema con ID 5 debe lanzar la excepción
        ProblemaNotFoundException ex = assertThrows(
                ProblemaNotFoundException.class,
                () -> problemaController.eliminarProblema(5L)
        );
        assertEquals("No existe", ex.getMessage());
    }
}
