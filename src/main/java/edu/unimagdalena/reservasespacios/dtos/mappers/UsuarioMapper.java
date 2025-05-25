package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "rol", target = "rol", ignore = true)
    @Mapping(source = "constrasena", target = "constrasena", ignore = true)
    Usuario CreateDTOToUsuario(UsuarioDTOCreate usuarioDTOCreate);

    @Mapping(source = "rol.rolEnum", target = "rol")
    UsuarioDTOResponse UsuarioToDTOResponse(Usuario usuario);
}
