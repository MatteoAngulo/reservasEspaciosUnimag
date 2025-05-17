package edu.unimagdalena.reservasespacios.services.interfaces;


import edu.unimagdalena.reservasespacios.dtos.requests.HorarioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioDtoResponse;

import java.util.List;

public interface HorarioService {

    HorarioDtoResponse findById(Long idHorario);
    List<HorarioDtoResponse> findAll();
    HorarioDtoResponse saveHorario(HorarioDtoRequest horarioDtoRequest);
    HorarioDtoResponse updateHorario(Long idHorario, HorarioDtoRequest horarioDtoRequest);
    void deleteHorario(Long idHorario);


}
