package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.usuario.UsuarioDTOCreate;
import edu.unimagdalena.reservasespacios.dtos.response.UsuarioDTOResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTOResponse> registrarUsuario(@RequestBody @Valid UsuarioDTOCreate usuarioDTOCreate) {
        return new ResponseEntity(usuarioService.saveUsuario(usuarioDTOCreate), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTOResponse> obtenerUsuarioPorId(@PathVariable
                                                 @NotNull(message = "El id no puede ser nulo")
                                                 @Positive(message = "El id debe ser positivo")
                                                 Long id){
        return ResponseEntity.ok(usuarioService.findUsuarioById(id));
    }

    @GetMapping("/por-correo")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTOResponse> obtenerUsuarioPorCorreo(@RequestParam @NotBlank(message = "El correo no puede estar en blanco")
                                                          String correo){
        return ResponseEntity.ok(usuarioService.findUsuarioByCorreo(correo));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTOResponse>> obtenerTodosLosUsuarios(){
        return ResponseEntity.ok(usuarioService.findUsuarios());
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTOResponse> actualizarUsuario(@RequestBody @Valid UsuarioDTOCreate usuarioDTOCreate){
        return ResponseEntity.ok(usuarioService.updateUsuario(usuarioDTOCreate));
    }

    @DeleteMapping("/")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> borrarUsuario(@RequestParam @NotBlank(message = "El correo no puede estar en blanco")
                                  String correo){
        usuarioService.deleteUsuario(correo);
        return ResponseEntity.noContent().build();
    }

}
