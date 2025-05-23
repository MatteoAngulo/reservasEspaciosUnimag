package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.exceptions.notFound.SedeNotFoundException;
import edu.unimagdalena.reservasespacios.services.interfaces.SedeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class SedeControllerTest {

    @Mock
    private SedeService sedeService;

    @InjectMocks
    private SedeController sedeController;

    private SedeDtoResponse dto1;
    private SedeDtoResponse dto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dto1 = new SedeDtoResponse(1L, "Principal");
        dto2 = new SedeDtoResponse(2L, "Secundaria");
    }

    @Test
    void getAllSedes_devuelveOkYLista() {
        when(sedeService.findAllSedes()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<List<SedeDtoResponse>> response = sedeController.getAllSedes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "El cuerpo no debe ser nulo");
        assertEquals(2, response.getBody().size(), "Debe contener dos elementos");
        assertEquals(dto1, response.getBody().get(0));
        verify(sedeService).findAllSedes();
    }

    @Test
    void getSedeById_devuelveOkYDto() {
        when(sedeService.findSedeById(1L)).thenReturn(dto1);

        ResponseEntity<SedeDtoResponse> response = sedeController.getSedeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto1, response.getBody());
        verify(sedeService).findSedeById(1L);
    }

    @Test
    void getSedeById_noEncontrada_lanzaExcepcion() {
        when(sedeService.findSedeById(99L)).thenThrow(new SedeNotFoundException("No existe"));

        SedeNotFoundException ex = assertThrows(SedeNotFoundException.class,
                () -> sedeController.getSedeById(99L),
                "Debe lanzar SedeNotFoundException");
        assertEquals("No existe", ex.getMessage());
    }

    @Test
    void createSede_devuelveCreatedYDto() {
        SedeDtoRequest request = new SedeDtoRequest("Nueva");
        SedeDtoResponse responseDto = new SedeDtoResponse(10L, "Nueva");
        when(sedeService.saveSede(any(SedeDtoRequest.class))).thenReturn(responseDto);

        ResponseEntity<SedeDtoResponse> response = sedeController.createSede(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(sedeService).saveSede(request);
    }

    @Test
    void updateSede_devuelveOkYDto() {
        SedeDtoRequest request = new SedeDtoRequest("Modificada");
        SedeDtoResponse responseDto = new SedeDtoResponse(1L, "Modificada");
        when(sedeService.updateSede(eq(1L), any(SedeDtoRequest.class))).thenReturn(responseDto);

        ResponseEntity<SedeDtoResponse> response = sedeController.updateSede(1L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(sedeService).updateSede(1L, request);
    }

    @Test
    void deleteSede_devuelveNoContent() {
        doNothing().when(sedeService).deleteSede(1L);

        ResponseEntity<Void> response = sedeController.deleteSede(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody(), "El cuerpo debe ser nulo");
        verify(sedeService).deleteSede(1L);
    }

    @Test
    void deleteSede_noEncontrada_lanzaExcepcion() {
        doThrow(new SedeNotFoundException("No existe")).when(sedeService).deleteSede(5L);

        SedeNotFoundException ex = assertThrows(SedeNotFoundException.class,
                () -> sedeController.deleteSede(5L),
                "Debe lanzar SedeNotFoundException");
        assertEquals("No existe", ex.getMessage());
    }
}
