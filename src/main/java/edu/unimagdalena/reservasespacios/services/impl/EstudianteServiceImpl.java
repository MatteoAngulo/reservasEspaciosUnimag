package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EstudianteMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOUpdate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.exceptions.UserAlreadyExists;
import edu.unimagdalena.reservasespacios.exceptions.notFound.UsuarioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;

    @Override
    public EstudianteDTOResponse saveEstudiante(EstudianteDTOCreate estudiante) {
        if (estudianteRepository.findByCorreo(estudiante.correo()).isPresent()) {
            throw new UserAlreadyExists("El estudiante con el correo " + estudiante.correo() + " ya existe");
        }

        return estudianteMapper.estudianteToDTOResponse(
                estudianteRepository.save(estudianteMapper.createDTOToEstudiante(estudiante))
        );
    }

    @Override
    public EstudianteDTOResponse findEstudianteById(Long id) {
        return estudianteRepository.findById(id)
                .map(estudianteMapper::estudianteToDTOResponse)
                .orElseThrow(() -> new UsuarioNotFoundException("El estudiante con el id " + id + " no existe"));
    }

    @Override
    public EstudianteDTOResponse findEstudianteByCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo)
                .map(estudianteMapper::estudianteToDTOResponse)
                .orElseThrow(() -> new UsuarioNotFoundException("El estudiante con el correo " + correo + " no existe"));
    }

    @Override
    public EstudianteDTOResponse findEstudianteByCodigoEstudiantil(Long codEstudiantil) {
        return estudianteRepository.findByCodigoEstudiantil(codEstudiantil)
                .map(estudianteMapper::estudianteToDTOResponse)
                .orElseThrow(() -> new UsuarioNotFoundException("El estudiante con el codigo " + codEstudiantil + " no existe"));
    }

    @Override
    public List<EstudianteDTOResponse> findEstudiantes() {
        return List.of();
    }

    @Override
    public EstudianteDTOResponse updateEstudiante(EstudianteDTOUpdate estudiante) {
        Estudiante estudianteToUpdate = estudianteRepository.findByCodigoEstudiantil(estudiante.codigoEstudiantil()).orElseThrow(() -> new UsuarioNotFoundException("El estudiante con el codigo estudiantil " + estudiante.codigoEstudiantil() + " no existe"));

        estudianteToUpdate.setNombre(estudiante.nombre());
        estudianteToUpdate.setContrasena(estudiante.contrasena());
        return estudianteMapper.estudianteToDTOResponse(estudianteRepository.save(estudianteToUpdate));
    }

    @Override
    public void deleteEstudiante(String correo) {
        if (estudianteRepository.findByCorreo(correo).isPresent()) {
            estudianteRepository.deleteByCorreo(correo);
        }else{
            throw new UsuarioNotFoundException("El estudiante con el correo " + correo + " no existe");
        }
    }
}
