package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Sede;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SedeMapper {

    //Entidad → DTO Response
    @Mapping(target = "espacioIds",
            expression = "java(sede.getEspacios() == null ? null : " +
                    "sede.getEspacios().stream()" +
                    ".map(e -> e.getIdEspacio())" +
                    ".collect(Collectors.toList()))")
    SedeDtoResponse sedeToDto(Sede sede);

    //DTO Request → Entidad
    @Mapping(target = "sedeId", ignore = true)
    @Mapping(target = "espacios", ignore = true)
    Sede dtoToSede(SedeDtoRequest dto);

    /**
     * Actualiza una entidad existente desde los datos del DTO.
     * Sólo nombre se copiará (espacios e id se dejan intactos).
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "espacios", ignore = true)
    void updateSedeFromDto(SedeDtoRequest dto, @MappingTarget Sede sede);
}
