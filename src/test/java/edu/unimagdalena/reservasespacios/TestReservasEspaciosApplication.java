package edu.unimagdalena.reservasespacios;

import org.springframework.boot.SpringApplication;

public class TestReservasEspaciosApplication {

    public static void main(String[] args) {
        SpringApplication.from(ReservasEspaciosApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
