package edu.unimagdalena.reservasespacios.exceptions.notFound;

public class ReservaNotFoundException extends ResourceNotFoundException {
    public ReservaNotFoundException(Long id) {
        super("Reserva con ID: " + id +" no encontrado");
    }
}
