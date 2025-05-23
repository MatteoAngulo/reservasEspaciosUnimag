package edu.unimagdalena.reservasespacios.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Rol;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import edu.unimagdalena.reservasespacios.enums.RolEnum;
import edu.unimagdalena.reservasespacios.services.interfaces.UsuarioService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@Import(UsuarioControllerTest.MockConfig.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig{
        @Bean
        public UsuarioService usuarioService(){
            return Mockito.mock(UsuarioService.class);
        }
    }

    Usuario usuario1;
    Usuario usuario2;
    UsuarioDTOResponse usuarioDTOResponse1;
    UsuarioDTOResponse usuarioDTOResponse2;

    @BeforeEach
    void setUp() {
        usuario1 = Usuario.builder()
                .usuarioId(1L)
                .correo("correo@gmail.com")
                .contrasena("contrasena")
                .rol(Rol.builder()
                        .rolEnum(RolEnum.ADMINISTRADOR)
                        .build())
                .build();

        usuario2 = Usuario.builder()
                .usuarioId(2L)
                .correo("correo2@gmail.com")
                .contrasena("contrasena")
                .rol(Rol.builder()
                        .rolEnum(RolEnum.ADMINISTRADOR)
                        .build())
                .build();

        usuarioDTOResponse1 = new UsuarioDTOResponse(usuario1.getCorreo(),usuario1.getRol().getRolEnum());
        usuarioDTOResponse2 = new UsuarioDTOResponse(usuario2.getCorreo(),usuario2.getRol().getRolEnum());
    }

    @Test
    void registrarUsuario() throws Exception {
        UsuarioDTOCreate usuarioDTOCreate = UsuarioDTOCreate.builder()
                .correo("correo@gmail.com")
                .contrasena("contrasena")
                .rol(RolEnum.ADMINISTRADOR)
                .build();
        when(usuarioService.saveUsuario(any(UsuarioDTOCreate.class))).thenReturn(usuarioDTOResponse1);

        mvc.perform(post("/api/usuarios/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTOCreate)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.correo").value("correo@gmail.com"));
    }

    @Test
    void obtenerUsuarioPorId() throws Exception {
        when(usuarioService.findUsuarioById(anyLong())).thenReturn(usuarioDTOResponse1);
        mvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("correo@gmail.com"));

    }

    @Test
    void obtenerUsuarioPorCorreo() throws Exception {
        when(usuarioService.findUsuarioByCorreo(anyString())).thenReturn(usuarioDTOResponse1);
        mvc.perform(get("/api/usuarios/por-correo?correo=correo@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("correo@gmail.com"));
    }

    @Test
    void obtenerTodosLosUsuarios() throws Exception {
        when(usuarioService.findUsuarios()).thenReturn(List.of(usuarioDTOResponse1, usuarioDTOResponse2));
        mvc.perform(get("/api/usuarios/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].correo").value("correo@gmail.com"));
    }

    @Test
    void actualizarUsuario() throws Exception {
        UsuarioDTOCreate usuarioDTOCreate = UsuarioDTOCreate.builder()
                .correo("correo@gmail.com")
                .contrasena("contrasena")
                .rol(RolEnum.ADMINISTRADOR)
                .build();
        when(usuarioService.updateUsuario(any(UsuarioDTOCreate.class))).thenReturn(usuarioDTOResponse1);
        mvc.perform(put("/api/usuarios/actualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTOCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("correo@gmail.com"));
    }

    @Test
    void borrarUsuario() throws Exception {
        mvc.perform(delete("/api/usuarios/borrar?correo=correo@gmail.com"))
                .andExpect(status().isNoContent());
        verify(usuarioService).deleteUsuario("correo@gmail.com");
    }
}