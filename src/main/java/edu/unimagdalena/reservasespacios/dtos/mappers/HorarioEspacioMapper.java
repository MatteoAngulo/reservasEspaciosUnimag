package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HorarioEspacioMapper {

    @Mapping(source = "espacio.idEspacio", target = "idEspacio")
    HorarioEspacioDtoResponse toDtoResponse(HorarioEspacio horarioEspacio);

    @Mapping(target = "espacio", ignore = true)
    HorarioEspacio toEntity(HorarioEspacioDtoRequest horarioEspacioDto);

    void updateHorarioEspacioFromDto(HorarioEspacioDtoRequest horarioEspacioDto, @MappingTarget HorarioEspacio horarioEspacio);
}
