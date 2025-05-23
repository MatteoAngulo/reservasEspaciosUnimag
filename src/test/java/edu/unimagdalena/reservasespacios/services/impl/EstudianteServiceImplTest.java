package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EstudianteMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.Rol;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import edu.unimagdalena.reservasespacios.enums.RolEnum;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import edu.unimagdalena.reservasespacios.repositories.RolRepository;
import edu.unimagdalena.reservasespacios.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceImplTest {

    @Mock
    EstudianteRepository estudianteRepository;
    @Mock
    UsuarioRepository usuarioRepository;
    @Mock
    RolRepository rolRepository;

    @Mock
    EstudianteMapper estudianteMapper;

    @InjectMocks
    EstudianteServiceImpl estudianteService;

    Usuario usuario1;
    Usuario usuario2;
    Estudiante estudiante1;
    Estudiante estudiante2;
    EstudianteDTOResponse estudianteDTOResponse1;
    EstudianteDTOResponse estudianteDTOResponse2;

    @BeforeEach
    void setUp() {
        usuario1 = Usuario.builder()
                .correo("pollo1@gmail.com")
                .contrasena("contrasena")
                .rol(Rol.builder().rolEnum(
                        RolEnum.ESTUDIANTE
                ).build())
                .build();

        estudiante1 = Estudiante.builder()
                .codigoEstudiantil(1L)
                .nombre("pollo 1")
                .usuario(usuario1)
                .idEstudiante(1L)
                .build();

        estudianteDTOResponse1 = new EstudianteDTOResponse(estudiante1.getNombre());

        usuario2 = Usuario.builder()
                .correo("pollo1@gmail.com")
                .contrasena("contrasena")
                .rol(Rol.builder().rolEnum(
                        RolEnum.ESTUDIANTE
                ).build())
                .build();

        estudiante2 = Estudiante.builder()
                .codigoEstudiantil(1L)
                .nombre("pollo 1")
                .usuario(usuario2)
                .idEstudiante(1L)
                .build();

        estudianteDTOResponse2 = new EstudianteDTOResponse(estudiante1.getNombre());
    }

    @Test
    void saveEstudiante() {

        EstudianteDTOCreate estudianteDTOCreate = EstudianteDTOCreate.builder()
                .codEstudiantil(1L)
                .nombre("pollo 1")
                .correo("pollo1@gmail.com")
                .contrasena("contrasena")
                .rol(RolEnum.ESTUDIANTE)
                .build();

        when(estudianteRepository.findByCodigoEstudiantil(any())).thenReturn(Optional.empty());
        when(usuarioRepository.findByCorreo(any())).thenReturn(Optional.empty());
        when(rolRepository.findByRol(any())).thenReturn(Optional.of(Rol.builder().rolEnum(RolEnum.ESTUDIANTE).build()));
        when(estudianteRepository.save(estudiante1)).thenReturn(estudiante1);
        when(estudianteMapper.estudianteToDTOResponse(estudiante1)).thenReturn(estudianteDTOResponse1);

        EstudianteDTOResponse response = estudianteService.saveEstudiante(estudianteDTOCreate);
        assertNotNull(response);
        assertEquals(response.nombre(),estudianteDTOCreate.nombre());
        verify(estudianteRepository,times(1)).save(estudiante1);
    }

    @Test
    void findEstudianteById() {
    }

    @Test
    void findEstudianteByCorreo() {
    }

    @Test
    void findEstudianteByCodigoEstudiantil() {
    }

    @Test
    void findEstudiantes() {
    }

    @Test
    void updateEstudiante() {
    }

    @Test
    void deleteEstudiante() {
    }
}