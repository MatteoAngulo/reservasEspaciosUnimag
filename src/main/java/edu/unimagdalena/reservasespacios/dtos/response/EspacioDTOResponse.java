package edu.unimagdalena.reservasespacios.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EspacioDTOResponse {
    private Long id;
    private String nombre;
    private String tipo;
    private String restricciones;

}
