package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EstudianteMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.Rol;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import edu.unimagdalena.reservasespacios.enums.RolEnum;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceImplTest {

    @Mock
    EstudianteRepository estudianteRepository;

    @Mock
    EstudianteMapper estudianteMapper;

    @InjectMocks
    EstudianteServiceImpl estudianteService;

    @BeforeEach
    void setUp() {
        Usuario usuario1 = Usuario.builder()
                .correo("pollo1@gmail.com")
                .contrasena("contrasena")
                .rol(Rol.builder().rolEnum(
                        RolEnum.ESTUDIANTE
                ).build())
                .build();

        Estudiante estudiante1 = Estudiante.builder()
                .codigoEstudiantil(1L)
                .nombre("pollo 1")
                .usuario(usuario1)
                .idEstudiante(1L)
                .build();

        EstudianteDTOResponse estudianteDTOResponse1 = new EstudianteDTOResponse(estudiante1.getNombre());

        Usuario usuario2 = Usuario.builder()
                .correo("pollo1@gmail.com")
                .contrasena("contrasena")
                .rol(Rol.builder().rolEnum(
                        RolEnum.ESTUDIANTE
                ).build())
                .build();

        Estudiante estudiante2 = Estudiante.builder()
                .codigoEstudiantil(1L)
                .nombre("pollo 1")
                .usuario(usuario2)
                .idEstudiante(1L)
                .build();

        EstudianteDTOResponse estudianteDTOResponse2 = new EstudianteDTOResponse(estudiante1.getNombre());
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