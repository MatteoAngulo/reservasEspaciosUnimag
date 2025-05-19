package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioSaveDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioUpdateEstadoDto;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface HorarioEspacioService {

    HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioSaveDtoRequest horarioDto);
    List<HorarioEspacioDtoResponse> findAllHorariosEspacios();
    HorarioEspacioDtoResponse findHorarioEspacioById(Long id);
    List<HorarioEspacioDtoResponse> findHorarioDisponiblesFechaYHora(LocalDate fecha, Long idEspacio);
    HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioDto);
    HorarioEspacioDtoResponse updateEstadoHorarioEspacio(Long id, HorarioEspacioUpdateEstadoDto estadoEspacio);
    void deleteHorarioEspacio(Long id);

    void validarSolapamiento(Long idEspacio, LocalTime nHoraIni, LocalTime nHoraFin, LocalDate fecha);
}
