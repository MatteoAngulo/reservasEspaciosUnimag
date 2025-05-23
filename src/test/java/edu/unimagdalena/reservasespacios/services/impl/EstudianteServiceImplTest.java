package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EstudianteMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
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


    @Test
    void saveEstudiante() {

        EstudianteDTOCreate estudianteDTOCreate = EstudianteDTOCreate.builder()
                .codEstudiantil(1L)
                .nombre("pollo 1")
                .correo("pollo1@gmail.com")
                .contrasena("contrasena")
                .build();

        Estudiante estudiante1 = Estudiante.builder()
                .codigoEstudiantil(estudianteDTOCreate.codEstudiantil())
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