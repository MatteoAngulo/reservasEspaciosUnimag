package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.UsuarioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Rol;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import edu.unimagdalena.reservasespacios.enums.RolEnum;
import edu.unimagdalena.reservasespacios.repositories.RolRepository;
import edu.unimagdalena.reservasespacios.repositories.UsuarioRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    UsuarioMapper usuarioMapper;

    @Mock
    RolRepository rolRepository;

    @InjectMocks
    UsuarioServiceImpl usuarioServiceImpl;

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
    void saveUsuario() {

        UsuarioDTOCreate usuarioDTOCreate = UsuarioDTOCreate.builder()
                .correo("correo@gmail.com")
                .contrasena("contrasena")
                .rol(RolEnum.ADMINISTRADOR)
                .build();

        when(usuarioMapper.CreateDTOToUsuario(usuarioDTOCreate)).thenReturn(usuario1);
        when(usuarioRepository.findByCorreo(anyString())).thenReturn(Optional.empty());
        when(rolRepository.findByRol(any(RolEnum.class))).thenReturn(Optional.of(Rol.builder()
                        .rolEnum(RolEnum.ADMINISTRADOR)
                .build()));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario1);
        when(usuarioMapper.UsuarioToDTOResponse(usuario1)).thenReturn(usuarioDTOResponse1);

        UsuarioDTOResponse response = usuarioServiceImpl.saveUsuario(usuarioDTOCreate);
        assertNotNull(response);
        assertEquals(response.correo(),usuario1.getCorreo());
        verify(usuarioRepository,times(1)).save(any(Usuario.class));
    }

    @Test
    void findUsuarioById() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario1));
        when(usuarioMapper.UsuarioToDTOResponse(usuario1)).thenReturn(usuarioDTOResponse1);
        UsuarioDTOResponse response = usuarioServiceImpl.findUsuarioById(1L);
        assertNotNull(response);
        assertEquals(response.correo(),usuario1.getCorreo());
        verify(usuarioRepository,times(1)).findById(anyLong());
    }

    @Test
    void findUsuarioByCorreo() {
        when(usuarioRepository.findByCorreo(anyString())).thenReturn(Optional.of(usuario1));
        when(usuarioMapper.UsuarioToDTOResponse(usuario1)).thenReturn(usuarioDTOResponse1);
        UsuarioDTOResponse response = usuarioServiceImpl.findUsuarioByCorreo("correo");
        assertNotNull(response);
        assertEquals(response.correo(),usuario1.getCorreo());
        verify(usuarioRepository,times(1)).findByCorreo(anyString());
    }

    @Test
    void findUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));
        when(usuarioMapper.UsuarioToDTOResponse(usuario1)).thenReturn(usuarioDTOResponse1);
        when(usuarioMapper.UsuarioToDTOResponse(usuario2)).thenReturn(usuarioDTOResponse2);
        List<UsuarioDTOResponse> usuarioDTOResponses = usuarioServiceImpl.findUsuarios();
        assertNotNull(usuarioDTOResponses);
        assertEquals(usuarioDTOResponses.size(),2);
        verify(usuarioRepository,times(1)).findAll();
    }

    @Test
    void updateUsuario() {
        UsuarioDTOCreate usuarioDTOCreate = UsuarioDTOCreate.builder()
                .correo("correo@gmail.com")
                .contrasena("contrasena")
                .rol(RolEnum.ADMINISTRADOR)
                .build();
        when(usuarioRepository.findByCorreo(anyString())).thenReturn(Optional.of(usuario1));
        usuario1.setContrasena("contrasenaA");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario1);
        usuario1.setContrasena("contrasena");
        when(usuarioMapper.UsuarioToDTOResponse(any(Usuario.class))).thenReturn(usuarioDTOResponse1);
        when(rolRepository.findByRol(any(RolEnum.class))).thenReturn(Optional.of(Rol.builder()
                .rolEnum(RolEnum.ADMINISTRADOR)
                .build()));
        UsuarioDTOResponse response = usuarioServiceImpl.updateUsuario(usuarioDTOCreate);


        assertNotNull(response);
        assertEquals(response.correo(),usuario1.getCorreo());
        verify(usuarioRepository,times(1)).save(any(Usuario.class));
    }

    @Test
    void deleteUsuario() {
        when(usuarioRepository.findByCorreo(anyString())).thenReturn(Optional.of(usuario1));
        doNothing().when(usuarioRepository).deleteByCorreo(anyString());
        usuarioServiceImpl.deleteUsuario(usuario1.getCorreo());
    }
}