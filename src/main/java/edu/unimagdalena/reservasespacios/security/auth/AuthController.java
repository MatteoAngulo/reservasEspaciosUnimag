package edu.unimagdalena.reservasespacios.security.auth;

import edu.unimagdalena.reservasespacios.dtos.requests.LoginRequestDTO;
import edu.unimagdalena.reservasespacios.dtos.response.LoginResponseDTO;
import edu.unimagdalena.reservasespacios.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authentication")
    public ResponseEntity<?> UserAuthenticate(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.correo(),
                            loginRequestDTO.contrasena())
            );
        }
        catch (Exception ex) {

            throw new Exception("Invalid username or password");

        }

        LoginResponseDTO response = new LoginResponseDTO(jwtUtil.generateToken(loginRequestDTO.correo()));

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }
}
