package edu.unimagdalena.reservasespacios.dtos.requests.estudiante;

public record EstudianteDTOCreate(
        Long codEstudiantil,
        String nombre,
        String correo,
        String contrasena
) {
}
