package edu.unimagdalena.reservasespacios.exceptions.notFound;

public class UsuarioNotFoundException extends ResourceNotFoundException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
