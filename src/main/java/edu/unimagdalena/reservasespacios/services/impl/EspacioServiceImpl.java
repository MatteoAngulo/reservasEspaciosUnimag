package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.EspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Sede;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.SedeNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.SedeRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.EspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Sede sede = sedeRepository.findById(espacioDTO.idSede())
                .orElseThrow(() -> new SedeNotFoundException("Sede con ID " + espacioDTO.idSede() + " no encontrada"));
        // 2) asignarla
        nuevo.setSede(sede);

        // 3) ahora sí persistir
        Espacio saved = repository.save(nuevo);
        return mapper.espacioToDTO(saved);
    }

    @Override
    public List<EspacioDTOResponse> findAllEspacios() {
        return repository.findAll()
                .stream()
                .map(mapper::espacioToDTO)
                .toList();
    }

    @Override
    public EspacioDTOResponse findEspacioById(Long idEspacio) {
        Espacio esp = repository.findById(idEspacio)
                .orElseThrow(() ->
                        new EspacioNotFoundException("Espacio con ID " + idEspacio + " no encontrado")
                );
        return mapper.espacioToDTO(esp);
    }

    @Override
    public EspacioDTOResponse updateEspacio(Long idEspacio, EspacioDTOResquests espacioDTO) {
        // 1) recuperar existente
        Espacio existente = repository.findById(idEspacio)
                .orElseThrow(() ->
                        new EspacioNotFoundException("Espacio con ID " + idEspacio + " no encontrado")
                );

        // 2) actualizar campos simples
        mapper.updateEspacioFromRequestDTO(espacioDTO, existente);

        // 3) si cambiaron de sede, recargarla
        if (espacioDTO.idSede() != null &&
                (existente.getSede() == null || !existente.getSede().getSedeId().equals(espacioDTO.idSede()))) {

            Sede nueva = sedeRepository.findById(espacioDTO.idSede())
                    .orElseThrow(() ->
                            new SedeNotFoundException("Sede con ID " + espacioDTO.idSede() + " no encontrada")
                    );
            existente.setSede(nueva);
        }

        // 4) guardar y devolver
        Espacio updated = repository.save(existente);
        return mapper.espacioToDTO(updated);
    }

    @Override
    public void deleteEspacio(Long idEspacio) {
        if (!repository.existsById(idEspacio)) {
            throw new EspacioNotFoundException("Espacio con ID " + idEspacio + " no encontrado");
        }
        repository.deleteById(idEspacio);
    }


}
