package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Sede;
import edu.unimagdalena.reservasespacios.enums.TipoEspacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class EspacioMapperTest {

    private EspacioMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(EspacioMapper.class);
    }

    @Test
    void testDtoToEspacio() {
        // Dado
        EspacioDTOResquests dto = new EspacioDTOResquests(
                "Sala 101",
                "SALONES",
                "Sin proyector",
                3L,
                true
        );

        // Cuando
        Espacio entidad = mapper.dtoToEspacio(dto);

        // Entonces
        assertNotNull(entidad, "La entidad no debe ser nula");
        assertNull(entidad.getIdEspacio(), "idEspacio debe ser nulo antes de persistir");
        assertEquals("Sala 101", entidad.getNombre());
        assertEquals(TipoEspacio.SALONES, entidad.getTipo(), "Debe mapear el String al enum");
        assertEquals("Sin proyector", entidad.getRestricciones());
        assertTrue(entidad.getDisponible(), "Debe mapear disponible correctamente");
        assertNull(entidad.getSede(), "La sede se asigna en el servicio y debe quedar nula");
    }

    @Test
    void testEspacioToDTO() {
        // Dado
        Sede sede = Sede.builder().sedeId(5L).name("Central").build();
        Espacio entidad = Espacio.builder()
                .idEspacio(8L)
                .nombre("Auditorio A")
                .tipo(TipoEspacio.AUDITORIOS)
                .restricciones(null)
                .disponible(false)
                .sede(sede)
                .build();

        // Cuando
        EspacioDTOResponse dto = mapper.espacioToDTO(entidad);

        // Entonces
        assertNotNull(dto);
        assertEquals(8L, dto.id());
        assertEquals("Auditorio A", dto.nombre());
        assertEquals("AUDITORIOS", dto.tipo());
        assertNull(dto.restricciones(), "Si en entidad es null, en DTO debe ser null");
        assertEquals(5L, dto.idSede());
        assertFalse(dto.disponible());
    }

    @Test
    void testUpdateEspacioFromRequestDTO() {
        // Dado
        Espacio existente = Espacio.builder()
                .idEspacio(2L)
                .nombre("Vieja")
                .tipo(TipoEspacio.CUBÍCULOS)
                .restricciones("Old")
                .disponible(false)
                .build();
        EspacioDTOResquests updateDto = new EspacioDTOResquests(
                "Nueva",
                "ZONAS_COMUNES",
                "Sin restricciones",
                4L,
                true
        );

        // Cuando
        mapper.updateEspacioFromRequestDTO(updateDto, existente);

        // Entonces
        assertEquals(2L, existente.getIdEspacio(), "idEspacio no debe cambiar");
        assertEquals("Nueva", existente.getNombre());
        assertEquals(TipoEspacio.ZONAS_COMUNES, existente.getTipo());
        assertEquals("Sin restricciones", existente.getRestricciones());
        assertTrue(existente.getDisponible());
        // sede sigue intacta (no mapeado)
        assertNull(existente.getSede(), "La sede no debe modificarse aquí");
    }
}
