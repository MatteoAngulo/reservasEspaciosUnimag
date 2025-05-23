package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Sede;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SedeMapperTest {

    private SedeMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(SedeMapper.class); //new SedeMapperImpl();
    }

    @Test
    void testSedeToDto() {
        // Given
        Sede sede = Sede.builder()
                .sedeId(1L)
                .name("Sede principal")
                .espacios(null)
                .build();

        // When
        SedeDtoResponse dto = mapper.sedeToDto(sede);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.id());
        assertEquals("Sede principal", dto.name());
    }

    @Test
    void testDtoToSede() {
        // Dado
        SedeDtoRequest request = new SedeDtoRequest("Sucursal Secundaria");

        // Cuando
        Sede entity = mapper.dtoToSede(request);

        // Entonces
        assertNotNull(entity, "La entidad no debe ser nula");
        assertNull(entity.getSedeId(), "sedeId debe ser nulo antes de la persistencia");
        assertEquals("Sucursal Secundaria", entity.getName(), "El nombre debe copiarse correctamente");
        assertNull(entity.getEspacios(), "espacios debe ser nulo (ignorando la lista)");
    }

    @Test
    void testUpdateSedeFromDto() {
        // Dado
        Sede existing = Sede.builder()
                .sedeId(5L)
                .name("Nombre Antiguo")
                .espacios(null)
                .build();
        SedeDtoRequest updateRequest = new SedeDtoRequest("Nombre Nuevo");

        // Cuando
        mapper.updateSedeFromDto(updateRequest, existing);

        // Entonces
        assertEquals(5L, existing.getSedeId(), "sedeId no debe cambiar");
        assertEquals("Nombre Nuevo", existing.getName(), "El nombre debe actualizarse correctamente");
    }



}