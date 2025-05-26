package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Problema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProblemaMapper {

    /**
     * Request DTO → Entidad
     * Ignoramos el ID (se genera en BD) y la relación Espacio,
     * que asignaremos en el servicio a partir de espacioId.
     */
    @Mapping(target = "idProblema", ignore = true)
    @Mapping(target = "espacio",       ignore = true)
    @Mapping(target = "estudiante", ignore = true)
    Problema toEntity(ProblemaDtoRequest dto);

    /**
     * Entidad → Response DTO
     * Extraemos espacio.idEspacio a espacioId en el DTO.
     */
    @Mapping(source = "espacio.idEspacio", target = "espacioId")
    @Mapping(source = "estudiante.idEstudiante", target = "idEstudiante")
    ProblemaDtoResponse toResponse(Problema problema);

    /**
     * Actualiza una entidad existente desde el Request DTO.
     * Sólo actualiza estado, descripción y fecha.
     * No toca ID ni la relación Espacio.
     */
    @Mapping(target = "idProblema", ignore = true)
    @Mapping(target = "espacio",       ignore = true)
    void updateFromDto(ProblemaDtoRequest dto, @MappingTarget Problema entity);
}
