package edu.unimagdalena.reservasespacios.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.requests.estudiante.EstudianteDTOUpdate;
import edu.unimagdalena.reservasespacios.dtos.response.EstudianteDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.Rol;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import edu.unimagdalena.reservasespacios.enums.RolEnum;
import edu.unimagdalena.reservasespacios.services.interfaces.EstudianteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteController.class)
@Import(EstudianteControllerTest.MockConfig.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig{
        @Bean
        public EstudianteService estudianteService(){
            return Mockito.mock(EstudianteService.class);
        }
    }

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
                .idEstudiante(2L)
                .build();

        estudianteDTOResponse2 = new EstudianteDTOResponse(estudiante1.getNombre());
    }

    @Test
    void registrarEstudiante() throws Exception {

        EstudianteDTOCreate estudianteDTOCreate = EstudianteDTOCreate.builder()
                .codEstudiantil(1L)
                .nombre("pollo 1")
                .correo("pollo1@gmail.com")
                .contrasena("contrasena")
                //.rol(RolEnum.ESTUDIANTE)
                .build();

        when(estudianteService.saveEstudiante(any(EstudianteDTOCreate.class))).thenReturn(estudianteDTOResponse1);

        mvc.perform(post("/api/estudiantes/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(estudianteDTOCreate)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("pollo 1"));
    }

    @Test
    void findEstudianteById() throws Exception {

        when(estudianteService.findEstudianteById(anyLong())).thenReturn(estudianteDTOResponse1);
        mvc.perform(get("/api/estudiantes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("pollo 1"));

    }

    @Test
    void findEstudianteByCodigo() throws Exception {
        when(estudianteService.findEstudianteByCodigoEstudiantil(anyLong())).thenReturn(estudianteDTOResponse1);
        mvc.perform(get("/api/estudiantes/por-codigo?codigo=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("pollo 1"));
    }

    @Test
    void findAllEstudiantes() throws Exception {
        when(estudianteService.findEstudiantes()).thenReturn(List.of(estudianteDTOResponse1,estudianteDTOResponse2));
        mvc.perform(get("/api/estudiantes/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("pollo 1"));
    }

    @Test
    void actualizarEstudiante() throws Exception {
        EstudianteDTOResponse estudianteDTOResponseA = new EstudianteDTOResponse("pollo 1a");
        EstudianteDTOUpdate estudianteDTOUpdate = new EstudianteDTOUpdate(1L,"pollo 1a","contrasena");

        when(estudianteService.updateEstudiante(any(EstudianteDTOUpdate.class))).thenReturn(estudianteDTOResponseA);
        mvc.perform(put("/api/estudiantes/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(estudianteDTOUpdate)))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("pollo 1a"));
    }

    @Test
    void borrarEstudianteByCodigo() throws Exception {
        mvc.perform(delete("/api/estudiantes/borrar?codigo=1"))
                .andExpect(status().isNoContent());
        verify(estudianteService,times(1)).deleteEstudianteByCodigo(1L);

    }
}