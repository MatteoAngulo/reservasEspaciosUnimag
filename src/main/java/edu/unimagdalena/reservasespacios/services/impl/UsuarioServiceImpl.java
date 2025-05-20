package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.UsuarioService;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    @Override
    public UsuarioDTOResponse saveUsuario(UsuarioDTOCreate usuario) {
        return null;
    }

    @Override
    public UsuarioDTOResponse findUsuarioById(Long id) {
        return null;
    }

    @Override
    public List<UsuarioDTOResponse> getUsuarios() {
        return List.of();
    }

    @Override
    public UsuarioDTOResponse updateUsuario(UsuarioDTOCreate usuario) {
        return null;
    }

    @Override
    public void deleteUsuario(Long id) {

    }
}
