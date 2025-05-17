package edu.unimagdalena.reservasespacios.dtos.mappers;

import ch.qos.logback.core.model.ComponentModel;
import edu.unimagdalena.reservasespacios.dtos.requests.HorarioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Horario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HorarioMapper {

    HorarioDtoResponse toHorarioDtoResponse(Horario horario);
    Horario toEntity(HorarioDtoRequest horarioDtoRequest);

}
