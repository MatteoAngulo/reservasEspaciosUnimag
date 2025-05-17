package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.EspacioService;
import org.springframework.stereotype.Service;

@Service
public class EspacioServiceImpl implements EspacioService {

    private final EspacioRepository repository;
    private final EspacioMapper mapper;

    public EspacioServiceImpl(EspacioRepository repository, EspacioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EspacioDTOResponse crearEspacio(EspacioDTOResquests espacioDTO) {
        Espacio nuevoEspacio = mapper.dtoToEspacio(espacioDTO);
        repository.save(nuevoEspacio);
        return mapper.espacioToDTO(nuevoEspacio);
    }


}
