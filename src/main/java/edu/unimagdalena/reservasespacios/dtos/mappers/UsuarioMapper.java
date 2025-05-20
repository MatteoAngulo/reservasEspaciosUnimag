package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario CreateDTOToUsuario(UsuarioDTOCreate usuarioDTOCreate);
    UsuarioDTOResponse UsuarioToDTOResponse(Usuario usuario);
}
