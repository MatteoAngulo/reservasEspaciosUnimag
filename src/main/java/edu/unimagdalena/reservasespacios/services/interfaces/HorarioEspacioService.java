package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;

import java.util.List;

public interface HorarioEspacioService {

    HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioSaveDtoRequest horarioDto);
    List<HorarioEspacioDtoResponse> findAllHorariosEspacios();
    HorarioEspacioDtoResponse findHorarioEspacioById(Long id);
    HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioDto);
    HorarioEspacioDtoResponse updateEstadoHorarioEspacio(Long id, HorarioEspacioUpdateEstadoDto estadoEspacio);
    void deleteHorarioEspacio(Long id);

}
