package edu.unimagdalena.reservasespacios.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemaDtoRequest {

    private Long espacioId;
    private String estado;
    private String descripcion;
    private Date fecha;
}
