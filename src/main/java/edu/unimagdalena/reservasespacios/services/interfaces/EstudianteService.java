package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOUpdate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;

import java.util.List;

public interface EstudianteService {

    EstudianteDTOResponse saveEstudiante(EstudianteDTOCreate estudiante);
    EstudianteDTOResponse findEstudianteById(Long id);
    EstudianteDTOResponse findEstudianteByCodigoEstudiantil(Long codEstudiantil);
    List<EstudianteDTOResponse> findEstudiantes();
    EstudianteDTOResponse updateEstudiante(EstudianteDTOUpdate estudiante);
    void deleteEstudianteByCodigo(Long codigo);
}
