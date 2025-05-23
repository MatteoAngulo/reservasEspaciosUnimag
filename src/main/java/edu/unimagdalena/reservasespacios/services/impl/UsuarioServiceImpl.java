package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.UsuarioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import edu.unimagdalena.reservasespacios.exceptions.UserAlreadyExists;
import edu.unimagdalena.reservasespacios.exceptions.notFound.UsuarioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.RolRepository;
import edu.unimagdalena.reservasespacios.repositories.UsuarioRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final RolRepository rolRepository;

    @Override
    public UsuarioDTOResponse saveUsuario(UsuarioDTOCreate usuario) {
        if(usuarioRepository.findByCorreo(usuario.correo()).isPresent()){
            throw new UserAlreadyExists("El correo ya esta registrado.");
        }

        return usuarioMapper
                .UsuarioToDTOResponse(
                        usuarioRepository.save(usuarioMapper.CreateDTOToUsuario(usuario))
                );
    }

    @Override
    public UsuarioDTOResponse findUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNotFoundException("El usuario con el id " + id + " no existe."));
        return usuarioMapper.UsuarioToDTOResponse(usuario);
    }

    @Override
    public UsuarioDTOResponse findUsuarioByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(usuarioMapper::UsuarioToDTOResponse)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario con el correo " + correo + " no existe."));
    }

    @Override
    public List<UsuarioDTOResponse> findUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::UsuarioToDTOResponse)
                .toList();
    }

    @Override
    public UsuarioDTOResponse updateUsuario(UsuarioDTOCreate usuario) {
        Usuario usuarioToUpdate = usuarioRepository.findByCorreo(usuario.correo()).orElseThrow(
                () -> new UsuarioNotFoundException("El usuario con el correo " + usuario.correo() + " no existe."));
        usuarioToUpdate.setContrasena(usuario.contrasena());
        usuarioToUpdate.setRol(rolRepository.findByRol(usuario.rol()).get());
        return usuarioMapper.UsuarioToDTOResponse(usuarioRepository.save(usuarioToUpdate));
    }

    @Override
    public void deleteUsuario(String correo) {
        if(usuarioRepository.findByCorreo(correo).isPresent()){
            usuarioRepository.deleteByCorreo(correo);
        }else{
            throw new UsuarioNotFoundException("El usuario con el id " + correo + " no existe.");
        }
    }
}
