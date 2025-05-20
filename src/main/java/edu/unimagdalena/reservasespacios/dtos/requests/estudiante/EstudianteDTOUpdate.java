package edu.unimagdalena.reservasespacios.dtos.requests.estudiante;

public record EstudianteDTOUpdate(
        Long codigoEstudiantil,
        String nombre,
        String contrasena
) {
}
