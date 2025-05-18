package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HorarioEspacioMapper {

    @Mapping(source = "horarioEspacio.idHorario", target = "idHorario")
    @Mapping(source = "horarioEspacio.idEspacio", target = "idEspacio")
    HorarioEspacioDtoResponse toHorarioEspacioDtoResponse(HorarioEspacio horarioEspacio);

    @Mapping(target = "horario", ignore = true)
    @Mapping(target = "espacio", ignore = true)
    HorarioEspacio toEntity(HorarioEspacioDtoRequest horarioEspacioDtoRequest);

}
