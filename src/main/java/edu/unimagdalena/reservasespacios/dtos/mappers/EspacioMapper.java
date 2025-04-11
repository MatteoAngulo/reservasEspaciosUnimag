package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EspacioMapper {

    EspacioDTOResponse espacioToDTO(Espacio espacio);

    @Mapping(target = "id", ignore = true )
    @Mapping(target = "sede", ignore = true )
    Espacio dtoToEspacio(EspacioDTOResquests espacioDTO);

    void updateEspacioFromRequestDTO(EspacioDTOResquests dto, @MappingTarget Espacio espacio);

}
