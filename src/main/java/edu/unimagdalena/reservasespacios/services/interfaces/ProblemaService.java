package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Problema;

import java.util.List;

public interface ProblemaService {
    //Admin
    List<ProblemaDtoResponse> findAllProblemas();
    ProblemaDtoResponse findProblemaById(Long idProblema);
    List<ProblemaDtoResponse> findProblemasPorEstudiante(Long idEst);
    List<ProblemaDtoResponse> findProblemasPorEstado(String estado);
    List<ProblemaDtoResponse> findProblemasPorEspacio(Long idEspacio);
    ProblemaDtoResponse updateProblema(Long idProblema, ProblemaDtoRequest problemaDtoRequest);
    void deleteProblema(Long idProblema);

    //estudiante
    ProblemaDtoResponse saveProblema(Long idEst, ProblemaDtoRequest problemaDtoRequest);
    void deleteProblemaEstudiante(Long idEst, Long idProblema);
}
