package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;

import java.time.LocalDate;
import java.util.List;

public interface HorarioEspacioService {

    HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioDtoRequest horarioDto);
    List<HorarioEspacioDtoResponse> findHorariosEspacios();
    HorarioEspacioDtoResponse findHorarioEspacioById(Long id);
    List<HorarioEspacioDtoResponse> findHorarioDisponibles(LocalDate fecha, Long idEspacio);
    HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioDto);
    void deleteHorarioEspacio(Long id);
}
