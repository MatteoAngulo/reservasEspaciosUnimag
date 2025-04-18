package edu.unimagdalena.reservasespacios.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Usuario{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrador;


    @Override
    public void iniciarSesion() {

    }
}
