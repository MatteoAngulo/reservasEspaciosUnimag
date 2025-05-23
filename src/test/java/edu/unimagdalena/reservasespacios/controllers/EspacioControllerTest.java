package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.SedeNotFoundException;
import edu.unimagdalena.reservasespacios.services.impl.EspacioServiceImpl;
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

// Pruebas unitarias para EspacioController usando Mockito
class EspacioControllerTest {

    @Mock
    // Simula la implementación concreta que inyecta el controlador
    private EspacioServiceImpl service;

    @InjectMocks
    private EspacioController espacioController;

    private EspacioDTOResponse resp1;
    private EspacioDTOResponse resp2;
    private EspacioDTOResquests req;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resp1 = new EspacioDTOResponse(1L, "Sala A", "SALONES", "Nada", 2L, true);
        resp2 = new EspacioDTOResponse(2L, "Cubículo B", "CUBÍCULOS", null, 3L, false);
        req = new EspacioDTOResquests("Sala A", "SALONES", "Nada", 2L, true);
    }

    @Test
    void listarEspacios_devuelveOkYLista() {
        // Dado: el servicio retorna una lista de espacios
        when(service.findAllEspacios()).thenReturn(List.of(resp1, resp2));

        // Cuando: se invoca el método listarEspacios
        ResponseEntity<List<EspacioDTOResponse>> response = espacioController.listarEspacios();

        // Entonces: se espera una respuesta HTTP 200 y una lista con dos elementos
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe responder HTTP 200");
        assertNotNull(response.getBody(), "El cuerpo no debe ser nulo");
        assertEquals(2, response.getBody().size(), "Debe devolver dos elementos");
        assertEquals(resp1, response.getBody().get(0));
        verify(service).findAllEspacios();
    }

    @Test
    void obtenerEspacio_devuelveOkYDto() {
        // Dado: el servicio retorna un espacio específico
        when(service.findEspacioById(1L)).thenReturn(resp1);

        // Cuando: se invoca obtenerEspacio con id 1
        ResponseEntity<EspacioDTOResponse> response = espacioController.obtenerEspacio(1L);

        // Entonces: se espera una respuesta HTTP 200 con el DTO correspondiente
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe responder HTTP 200");
        assertEquals(resp1, response.getBody(), "El cuerpo debe contener el DTO esperado");
        verify(service).findEspacioById(1L);
    }

    @Test
    void obtenerEspacio_noExiste_lanzaExcepcion() {
        // Dado: el servicio lanza una excepción cuando no encuentra el espacio
        when(service.findEspacioById(anyLong())).thenThrow(new EspacioNotFoundException("No existe"));

        // Cuando: se invoca obtenerEspacio con un id inexistente
        // Entonces: se espera que se lance una excepción EspacioNotFoundException
        EspacioNotFoundException ex = assertThrows(EspacioNotFoundException.class,
                () -> espacioController.obtenerEspacio(9L),
                "Debe lanzar EspacioNotFoundException");
        assertEquals("No existe", ex.getMessage());
    }

    @Test
    void crerEspacio_devuelveOkYDto() {
        // Dado: el servicio crea un espacio y lo retorna
        when(service.crearEspacio(any(EspacioDTOResquests.class))).thenReturn(resp1);

        // Cuando: se invoca crerEspacio con una solicitud válida
        ResponseEntity<EspacioDTOResponse> response = espacioController.crerEspacio(req);

        // Entonces: se espera una respuesta HTTP 200 con el DTO creado
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe responder HTTP 200");
        assertEquals(resp1, response.getBody(), "El cuerpo debe contener el DTO creado");
        verify(service).crearEspacio(req);
    }

    @Test
    void actualizarEspacio_devuelveOkYDto() {
        // Dado: el servicio actualiza un espacio y lo retorna
        when(service.updateEspacio(eq(1L), any(EspacioDTOResquests.class))).thenReturn(resp2);

        // Cuando: se invoca actualizarEspacio con un id y solicitud válida
        ResponseEntity<EspacioDTOResponse> response = espacioController.actualizarEspacio(1L, req);

        // Entonces: se espera una respuesta HTTP 200 con el DTO actualizado
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe responder HTTP 200");
        assertEquals(resp2, response.getBody(), "El cuerpo debe contener el DTO actualizado");
        verify(service).updateEspacio(1L, req);
    }

    @Test
    void eliminarEspacio_devuelveNoContent() {
        // Dado: el servicio elimina un espacio sin errores
        doNothing().when(service).deleteEspacio(1L);

        // Cuando: se invoca eliminarEspacio con un id válido
        ResponseEntity<Void> response = espacioController.eliminarEspacio(1L);

        // Entonces: se espera una respuesta HTTP 204 sin cuerpo
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "Debe responder HTTP 204");
        assertNull(response.getBody(), "El cuerpo debe ser nulo");
        verify(service).deleteEspacio(1L);
    }

    @Test
    void listarPorSede_devuelveOkYLista() {
        // Dado: el servicio retorna una lista de espacios para una sede específica
        when(service.findEspaciosBySede(2L)).thenReturn(List.of(resp1));

        // Cuando: se invoca listarPorSede con id de sede 2
        ResponseEntity<List<EspacioDTOResponse>> response = espacioController.listarPorSede(2L);

        // Entonces: se espera una respuesta HTTP 200 con una lista de espacios
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe responder HTTP 200");
        assertEquals(1, response.getBody().size(), "Debe devolver un elemento");
        assertEquals(resp1, response.getBody().get(0));
        verify(service).findEspaciosBySede(2L);
    }

    @Test
    void listarPorSede_sedeNoExiste_lanzaExcepcion() {
        // Dado: el servicio lanza una excepción cuando la sede no existe
        when(service.findEspaciosBySede(anyLong())).thenThrow(new SedeNotFoundException("No existe sede"));

        // Cuando: se invoca listarPorSede con un id de sede inexistente
        // Entonces: se espera que se lance una excepción SedeNotFoundException
        SedeNotFoundException ex = assertThrows(SedeNotFoundException.class,
                () -> espacioController.listarPorSede(5L),
                "Debe lanzar SedeNotFoundException");
        assertEquals("No existe sede", ex.getMessage());
    }
}
