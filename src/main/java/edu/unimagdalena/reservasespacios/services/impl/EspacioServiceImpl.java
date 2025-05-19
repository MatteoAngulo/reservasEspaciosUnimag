package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Sede;
import edu.unimagdalena.reservasespacios.exceptions.notFound.SedeNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.SedeRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.EspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspacioServiceImpl implements EspacioService {

    private final EspacioRepository repository;
    private final EspacioMapper mapper;
    private final SedeRepository sedeRepository;

    @Override
    public EspacioDTOResponse crearEspacio(EspacioDTOResquests espacioDTO) {
        Espacio nuevo = mapper.dtoToEspacio(espacioDTO);

        // 1) cargar la sede o lanzar excepción si no existe
        Sede sede = sedeRepository.findById(espacioDTO.getSedeId())
                .orElseThrow(() -> new SedeNotFoundException("Sede con ID " + espacioDTO.getSedeId() + " no encontrada"));
        // 2) asignarla
        nuevo.setSede(sede);

        // 3) ahora sí persistir
        Espacio saved = repository.save(nuevo);
        return mapper.espacioToDTO(saved);
    }


}
