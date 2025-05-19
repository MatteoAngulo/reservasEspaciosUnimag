package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Problema;

import java.util.List;

public interface ProblemaService {
    List<ProblemaDtoResponse> findAllProblemas();
    ProblemaDtoResponse findProblemaById(Long idProblema);
    ProblemaDtoResponse saveProblema(ProblemaDtoRequest problemaDtoRequest);
    ProblemaDtoResponse updateProblema(Long idProblema, ProblemaDtoRequest problemaDtoRequest);
    void deleteProblema(Long idProblema);
}
