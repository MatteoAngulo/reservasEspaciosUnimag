package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.SedeMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Sede;
import edu.unimagdalena.reservasespacios.exceptions.notFound.SedeNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.SedeRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.SedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SedeServiceImpl implements SedeService {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    @Override
    public List<SedeDtoResponse> findAllSedes() {
        return sedeRepository.findAll()
                .stream()
                .map(sedeMapper::sedeToDto)
                .toList();
    }

    @Override
    public SedeDtoResponse findSedeById(Long idSede) {
        Sede sede = sedeRepository.findById(idSede)
                .orElseThrow(() ->
                        new SedeNotFoundException("Sede con ID " + idSede + " no encontrada")
                );
        return sedeMapper.sedeToDto(sede);
    }

    @Override
    public SedeDtoResponse saveSede(SedeDtoRequest sedeDtoRequest) {
        // Mapear DTO → entidad
        Sede nueva = sedeMapper.dtoToSede(sedeDtoRequest);
        // Persistir
        Sede saved = sedeRepository.save(nueva);
        // Entidad → DTO
        return sedeMapper.sedeToDto(saved);
    }

    @Override
    public SedeDtoResponse updateSede(Long idSede, SedeDtoRequest sedeDtoRequest) {
        // 1) Recuperar existente o lanzar excepción
        Sede existente = sedeRepository.findById(idSede)
                .orElseThrow(() ->
                        new SedeNotFoundException("Sede con ID " + idSede + " no encontrada")
                );
        // 2) Aplicar cambios (solo nombre)
        sedeMapper.updateSedeFromDto(sedeDtoRequest, existente);
        // 3) Guardar y devolver DTO
        Sede updated = sedeRepository.save(existente);
        return sedeMapper.sedeToDto(updated);
    }

    @Override
    public void deleteSede(Long idSede) {
        if (!sedeRepository.existsById(idSede)) {
            throw new SedeNotFoundException("Sede con ID " + idSede + " no encontrada");
        }
        sedeRepository.deleteById(idSede);
    }
}
