package edu.unimagdalena.reservasespacios.security.auth;

import edu.unimagdalena.reservasespacios.dtos.requests.LoginRequestDTO;
import edu.unimagdalena.reservasespacios.dtos.response.LoginResponseDTO;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
import edu.unimagdalena.reservasespacios.repositories.UsuarioRepository;
import edu.unimagdalena.reservasespacios.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final EstudianteRepository estudianteRepository;

    @PostMapping("/authentication")
    public ResponseEntity<?> UserAuthenticate(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.correo(),
                            loginRequestDTO.contrasena())
            );
        } catch (Exception ex) {

            throw new Exception("Invalid username or password");
        }

        Optional<Estudiante> estudianteBandera = estudianteRepository.findByUsuarioUsuarioId(usuarioRepository.findByCorreo(loginRequestDTO.correo()).get().getUsuarioId());
        Long idEstudiante = -1L;
        if (estudianteBandera.isPresent()) {
            idEstudiante = estudianteBandera.get().getIdEstudiante();
        }

        LoginResponseDTO response = new LoginResponseDTO(jwtUtil.generateToken(loginRequestDTO.correo()), usuarioRepository.findByCorreo(loginRequestDTO.correo()).get().getRol().getRolEnum(), idEstudiante);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }
}
