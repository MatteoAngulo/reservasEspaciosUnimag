package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioSaveDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioUpdateEstadoDto;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.EstadoEspacio;

import java.time.LocalDate;
import java.util.List;

public interface HorarioEspacioService {

    HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioSaveDtoRequest horarioDto);
    List<HorarioEspacioDtoResponse> findAllHorariosEspacios();
    HorarioEspacioDtoResponse findHorarioEspacioById(Long id);
    List<HorarioEspacioDtoResponse> findHorarioDisponiblesFechaYHora(LocalDate fecha, Long idEspacio);
    HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioDto);
    HorarioEspacioDtoResponse updateEstadoHorarioEspacio(Long id, HorarioEspacioUpdateEstadoDto estadoEspacio);
    void deleteHorarioEspacio(Long id);
}
