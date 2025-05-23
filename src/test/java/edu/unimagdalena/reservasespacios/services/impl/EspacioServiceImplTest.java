package edu.unimagdalena.reservasespacios.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.unimagdalena.reservasespacios.dtos.mappers.EspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EspacioServiceImplTest {

    @Mock
    private EspacioRepository repositorio;

    @Mock
    private EspacioMapper mapper;

    @InjectMocks
    private EspacioServiceImpl espacioService;

    public EspacioServiceImplTest(){
        MockitoAnnotations.openMocks(this);
    }



}