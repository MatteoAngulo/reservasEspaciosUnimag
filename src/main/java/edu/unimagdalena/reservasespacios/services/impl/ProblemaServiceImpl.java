package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.mappers.ProblemaMapper;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Problema;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ProblemaNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.ProblemaRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.ProblemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemaServiceImpl implements ProblemaService {

    private final ProblemaRepository problemaRepository;
    private final EspacioRepository espacioRepository;
    private final ProblemaMapper mapper;

    @Override
    public List<ProblemaDtoResponse> findAllProblemas() {
        return problemaRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ProblemaDtoResponse findProblemaById(Long idProblema) {
        Problema problema = problemaRepository.findById(idProblema)
                .orElseThrow(() ->
                        new ProblemaNotFoundException("Problema con ID " + idProblema + " no encontrado")
                );
        return mapper.toResponse(problema);
    }

    @Override
    public ProblemaDtoResponse saveProblema(ProblemaDtoRequest dto) {
        // 1) mapear DTO → entidad (sin espacio)
        Problema nueva = mapper.toEntity(dto);

        // 2) cargar espacio o lanzar excepción
        Espacio espacio = espacioRepository.findById(dto.getEspacioId())
                .orElseThrow(() ->
                        new EspacioNotFoundException("Espacio con ID " + dto.getEspacioId() + " no encontrado")
                );
        nueva.setEspacio(espacio);

        // 3) persistir
        Problema saved = problemaRepository.save(nueva);
        return mapper.toResponse(saved);
    }

    @Override
    public ProblemaDtoResponse updateProblema(Long idProblema, ProblemaDtoRequest dto) {
        // 1) recuperar existente
        Problema existente = problemaRepository.findById(idProblema)
                .orElseThrow(() ->
                        new ProblemaNotFoundException("Problema con ID " + idProblema + " no encontrado")
                );

        // 2) actualizar campos simples
        mapper.updateFromDto(dto, existente);

        // 3) si cambió el espacio asociado, recargarlo
        if (dto.getEspacioId() != null &&
                (existente.getEspacio() == null || !existente.getEspacio().getIdEspacio().equals(dto.getEspacioId()))) {

            Espacio nuevoEsp = espacioRepository.findById(dto.getEspacioId())
                    .orElseThrow(() ->
                            new EspacioNotFoundException("Espacio con ID " + dto.getEspacioId() + " no encontrado")
                    );
            existente.setEspacio(nuevoEsp);
        }

        // 4) guardar y devolver
        Problema updated = problemaRepository.save(existente);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteProblema(Long idProblema) {
        if (!problemaRepository.existsById(idProblema)) {
            throw new ProblemaNotFoundException("Problema con ID " + idProblema + " no encontrado");
        }
        problemaRepository.deleteById(idProblema);
    }
}
