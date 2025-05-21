package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;

import java.time.DayOfWeek;
import java.util.List;

public interface HorarioEspacioService {

    HorarioEspacioDtoResponse findHorarioEspacioById(Long id);
    List<HorarioEspacioDtoResponse> findAllHorarioEspacio();
    List<HorarioEspacioDtoResponse> findHorariosPorDia(DayOfWeek dia);
    List<HorarioEspacioDtoResponse> findHorariosPorEspacio(Long idEspacio);
    List<HorarioEspacioDtoResponse> findHorarioPorEspacioYDia(DayOfWeek dia, Long idEspacio);
    HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioDtoRequest horarioEspacioDto);
    HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioEspacioDto);
    void deleteHorarioEspacio(Long id);

}
