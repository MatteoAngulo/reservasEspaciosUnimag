package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.Problema;
import edu.unimagdalena.reservasespacios.enums.EstadoProblema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProblemaMapperTest {

    private ProblemaMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ProblemaMapper.class);
    }

    @Test
    void toEntity_deDtoAEntidad() {
        // Dado: un DTO de petición
        Date fecha = Date.valueOf("2025-05-20");
        ProblemaDtoRequest dto = new ProblemaDtoRequest(
                7L,
                "EN_PROCESO",
                "Descripción válida",
                fecha
        );

        // Cuando: se convierte a entidad
        Problema entity = mapper.toEntity(dto);

        // Entonces: los campos simples deben mapearse, idProblema, espacio y estudiante quedan nulos
        assertNull(entity.getIdProblema(), "idProblema debe ser nulo antes de persistir");
        assertNull(entity.getEspacio(), "espacio debe quedar nulo y asignarse en el servicio");
        assertNull(entity.getEstudiante(), "estudiante debe quedar nulo y asignarse en el servicio");
        assertEquals(EstadoProblema.EN_PROCESO, entity.getEstado(), "Debe mapear el String al enum");
        assertEquals("Descripción válida", entity.getDescripcion());
        assertEquals(fecha, entity.getFecha());
    }

    @Test
    void toResponse_deEntidadADto() {
        // Dado: una entidad Problema con relaciones a Espacio y Estudiante
        Date fecha = Date.valueOf("2025-05-20");
        Espacio espacio = Espacio.builder().idEspacio(3L).build();
        Estudiante estudiante = Estudiante.builder().idEstudiante(42L).build();
        Problema entity = Problema.builder()
                .idProblema(5L)
                .espacio(espacio)
                .estudiante(estudiante)
                .estado(EstadoProblema.RESUELTO)
                .descripcion("Algo resuelto")
                .fecha(fecha)
                .build();

        // Cuando: se convierte a DTO de respuesta
        ProblemaDtoResponse dto = mapper.toResponse(entity);

        // Entonces: los campos deben copiarse correctamente, incluyendo espacioId y estudianteId
        assertEquals(5L, dto.idProblema());
        assertEquals(3L, dto.espacioId());
        assertEquals(42L, dto.idEstudiante());
        assertEquals("RESUELTO", dto.estado());
        assertEquals("Algo resuelto", dto.descripcion());
        assertEquals(fecha, dto.fecha());
    }

    @Test
    void updateFromDto_actualizaCampos() {
        // Dado: una entidad existente y un DTO de actualización
        Date oldDate = Date.valueOf("2025-05-19");
        Problema entity = Problema.builder()
                .idProblema(10L)
                .espacio(null)
                .estudiante(null)
                .estado(EstadoProblema.PENDIENTE)
                .descripcion("Vieja")
                .fecha(oldDate)
                .build();
        Date newDate = Date.valueOf("2025-05-21");
        ProblemaDtoRequest dto = new ProblemaDtoRequest(
                9L,
                "CERRADO",
                "Nueva descripción",
                newDate
        );

        // Cuando: se aplica la actualización
        mapper.updateFromDto(dto, entity);

        // Entonces: idProblema, espacio y estudiante no cambian, solo los campos editables
        assertEquals(10L, entity.getIdProblema(), "idProblema no debe cambiar");
        assertNull(entity.getEspacio(), "espacio no debe modificarse aquí");
        assertNull(entity.getEstudiante(), "estudiante no debe modificarse aquí");
        assertEquals(EstadoProblema.CERRADO, entity.getEstado());
        assertEquals("Nueva descripción", entity.getDescripcion());
        assertEquals(newDate, entity.getFecha());
    }
}
