package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.response.HistorialReservaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.HistorialReserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistorialReservaMapper {

    @Mapping(source = "reserva.idReserva", target = "idReserva")
    HistorialReservaDtoResponse toDtoResponse(HistorialReserva historialReserva);

}
