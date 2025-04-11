package edu.unimagdalena.reservasespacios.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EspacioDTOResquests {
    private String nombre;
    private String tipo;
    private String restricciones;

}
