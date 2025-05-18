package edu.unimagdalena.reservasespacios.exceptions.notFound;

public class EspacioNotFoundException extends ResourceNotFoundException {
    public EspacioNotFoundException(String message) {
        super(message);
    }
}
