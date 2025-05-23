package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Sede;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface SedeMapper {

    // Entidad → DTO Response: id y name se mapean automáticamente
    @Mapping(source = "sedeId", target = "id")
    SedeDtoResponse sedeToDto(Sede sede);

    // DTO Request → Entidad: ignoramos el id (se genera en BD) y la lista de espacios
    @Mapping(target = "sedeId",      ignore = true)
    @Mapping(target = "espacios", ignore = true)
    Sede dtoToSede(SedeDtoRequest dto);

    // Para update: igual, no tocamos id ni espacios
    @Mapping(target = "sedeId", ignore = true)
    @Mapping(target = "espacios", ignore = true)
    void updateSedeFromDto(SedeDtoRequest dto, @MappingTarget Sede sede);
}
