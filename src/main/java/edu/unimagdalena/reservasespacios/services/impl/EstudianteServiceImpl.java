package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EstudianteMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOUpdate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import edu.unimagdalena.reservasespacios.enums.RolEnum;
import edu.unimagdalena.reservasespacios.exceptions.UserAlreadyExists;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EstudianteNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.UsuarioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import edu.unimagdalena.reservasespacios.repositories.RolRepository;
import edu.unimagdalena.reservasespacios.repositories.UsuarioRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final EstudianteMapper estudianteMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EstudianteDTOResponse saveEstudiante(EstudianteDTOCreate estudiante) {
        if (estudianteRepository.findByCodigoEstudiantil(estudiante.codEstudiantil()).isPresent()) {
            throw new UserAlreadyExists("El estudiante con el codigo " + estudiante.codEstudiantil() + " ya existe");
        }
        if (usuarioRepository.findByCorreo(estudiante.correo()).isPresent()) {
            throw new UserAlreadyExists("El usuario con el correo "+estudiante.correo()+" ya existe");
        }

        Usuario usuario1 = Usuario.builder()
                .correo(estudiante.correo())
                .contrasena(passwordEncoder.encode(estudiante.contrasena()))
                .rol(rolRepository.findByRolEnum(RolEnum.ESTUDIANTE).get())
                .build();

        Estudiante estudiante1 = Estudiante.builder()
                .codigoEstudiantil(estudiante.codEstudiantil())
                .nombre(estudiante.nombre())
                .usuario(usuario1)
                .build();

        EstudianteDTOResponse response = estudianteMapper.estudianteToDTOResponse(estudianteRepository.save(estudiante1));
        System.out.println(response);
        return response;
    }

    @Override
    public EstudianteDTOResponse findEstudianteById(Long id) {
        return estudianteRepository.findById(id)
                .map(estudianteMapper::estudianteToDTOResponse)
                .orElseThrow(() -> new EstudianteNotFoundException("El estudiante con el id " + id + " no existe"));
    }

    @Override
    public EstudianteDTOResponse findEstudianteByCodigoEstudiantil(Long codEstudiantil) {
        return estudianteRepository.findByCodigoEstudiantil(codEstudiantil)
                .map(estudianteMapper::estudianteToDTOResponse)
                .orElseThrow(() -> new EstudianteNotFoundException("El estudiante con el codigo " + codEstudiantil + " no existe"));
    }

    @Override
    public List<EstudianteDTOResponse> findEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(estudianteMapper::estudianteToDTOResponse)
                .toList();
    }

    @Override
    public EstudianteDTOResponse updateEstudiante(EstudianteDTOUpdate estudiante) {
        Estudiante estudianteToUpdate = estudianteRepository.findByCodigoEstudiantil(estudiante.codigoEstudiantil()).orElseThrow(() -> new EstudianteNotFoundException("El estudiante con el codigo estudiantil " + estudiante.codigoEstudiantil() + " no existe"));

        estudianteToUpdate.setNombre(estudiante.nombre());
        estudianteToUpdate.getUsuario().setContrasena(passwordEncoder.encode(estudiante.contrasena()));
        return estudianteMapper.estudianteToDTOResponse(estudianteRepository.save(estudianteToUpdate));
    }

    @Override
    public void deleteEstudianteByCodigo(Long codigo) {
        if (estudianteRepository.findByCodigoEstudiantil(codigo).isPresent()) {
            estudianteRepository.deleteByCodigoEstudiantil(codigo);
        }else{
            throw new UsuarioNotFoundException("El estudiante con el codigo " + codigo + " no existe");
        }
    }
}
