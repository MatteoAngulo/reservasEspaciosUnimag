package edu.unimagdalena.reservasespacios.exceptions.notFound;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
