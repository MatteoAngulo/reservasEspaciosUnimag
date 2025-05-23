package edu.unimagdalena.reservasespacios.dtos.mappers;


import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EspacioMapperTest {
    private EspacioMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new EspacioMapperImpl();
    }



}