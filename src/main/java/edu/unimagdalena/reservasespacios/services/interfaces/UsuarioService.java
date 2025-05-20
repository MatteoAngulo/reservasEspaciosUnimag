package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioDTOResponse saveUsuario(UsuarioDTOCreate usuario);
    UsuarioDTOResponse findUsuarioById(Long id);
    UsuarioDTOResponse findUsuarioByCorreo(String correo);
    List<UsuarioDTOResponse> findUsuarios();
    UsuarioDTOResponse updateUsuario(UsuarioDTOCreate usuario);
    void deleteUsuario(String correo);
}
