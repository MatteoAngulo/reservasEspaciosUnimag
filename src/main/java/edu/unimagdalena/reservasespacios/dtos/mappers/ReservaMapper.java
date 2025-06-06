package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaEstDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.reserva.ReservaUpdateDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.response.ReservaEstDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(source = "estudiante.idEstudiante", target = "idEstudiante")
    @Mapping(source = "horarioEspacio.idHorarioEspacio", target = "idHorarioEspacio")
    ReservaDtoResponse toReservaDtoResponse(Reserva reserva);

    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "horarioEspacio", ignore = true)
    @Mapping(target = "estadoReserva", ignore = true)
    Reserva toEntity(ReservaDtoRequest reservaDtoRequest);


    @Mapping(source = "horarioEspacio.idHorarioEspacio", target = "idHorarioEspacio")
    ReservaEstDtoResponse toReservaEstDtoResponse(Reserva reserva);

    @Mapping(target = "estudiante", ignore = true)
    @Mapping(target = "horarioEspacio", ignore = true)
    @Mapping(target = "estadoReserva", ignore = true)
    Reserva toEntityEst(ReservaEstDtoRequest reservaEstDtoRequest);

}
