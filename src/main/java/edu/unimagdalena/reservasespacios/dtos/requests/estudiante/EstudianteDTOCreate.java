package edu.unimagdalena.reservasespacios.dtos.requests.estudiante;

public record EstudianteDTOCreate(
        int codEstudiantil,
        String nombre,
        String correo,
        String contrasena
) {
}
