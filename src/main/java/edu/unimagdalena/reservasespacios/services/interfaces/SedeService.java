package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;

import java.util.List;

public interface SedeService {
    List<SedeDtoResponse> findAllSedes();
    SedeDtoResponse findSedeById(Long idSede);
    SedeDtoResponse saveSede(SedeDtoRequest sedeDtoRequest);
    SedeDtoResponse updateSede(Long idSede, SedeDtoRequest sedeDtoRequest);
    void deleteSede(Long idSede);
}
