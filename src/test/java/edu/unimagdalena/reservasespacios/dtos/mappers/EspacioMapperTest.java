package edu.unimagdalena.reservasespacios.dtos.mappers;


import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Sede;
import edu.unimagdalena.reservasespacios.services.implementss.EspacioServiceImpl;
import edu.unimagdalena.reservasespacios.services.interfaces.EspacioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EspacioMapperTest {
    private EspacioMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new EspacioMapperImpl();
    }

    @Test
    void testEspacioToDto() {
        Espacio espacioPrueba = Espacio.builder().id(1L)
                .nombre("Cancha futbol 1")
                .restricciones("no se puede jugar despues de 8").build();

        EspacioDTOResponse dto = mapper.espacioToDTO(espacioPrueba);

        assertThat(dto.getId()).isEqualTo(espacioPrueba.getId());
        assertThat(dto.getNombre()).isEqualTo(espacioPrueba.getNombre());
        assertThat(dto.getRestricciones()).isEqualTo(espacioPrueba.getRestricciones());
    }

}