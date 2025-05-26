package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.dtos.mappers.ProblemaMapper;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.Estudiante;
import edu.unimagdalena.reservasespacios.entities.Problema;
import edu.unimagdalena.reservasespacios.enums.EstadoProblema;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EstudianteNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.ProblemaNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.EstudianteRepository;
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
    private final EstudianteRepository estudianteRepository;
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
    public List<ProblemaDtoResponse> findProblemasPorEstudiante(Long idEst) {
        return problemaRepository.findByEstudiante_IdEstudiante(idEst)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ProblemaDtoResponse> findProblemasPorEstado(String estado) {
        EstadoProblema estEnum = EstadoProblema.valueOf(estado);
        return problemaRepository.findByEstado(estEnum)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ProblemaDtoResponse> findProblemasPorEspacio(Long idEspacio) {
        return problemaRepository.findByEspacio_IdEspacio(idEspacio)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ProblemaDtoResponse updateProblema(Long idProblema, ProblemaDtoRequest dto) {
        Problema existente = problemaRepository.findById(idProblema)
                .orElseThrow(() -> new ProblemaNotFoundException("Problema con ID " + idProblema + " no encontrado"));
        mapper.updateFromDto(dto, existente);
        // actualizar espacio si cambió
        if (dto.espacioId() != null && !existente.getEspacio().getIdEspacio().equals(dto.espacioId())) {
            var esp = espacioRepository.findById(dto.espacioId())
                    .orElseThrow(() -> new EspacioNotFoundException("Espacio con ID " + dto.espacioId() + " no encontrado"));
            existente.setEspacio(esp);
        }
        Problema saved = problemaRepository.save(existente);
        return mapper.toResponse(saved);
    }

    @Override
    public void deleteProblema(Long idProblema) {
        if (!problemaRepository.existsById(idProblema)) {
            throw new ProblemaNotFoundException("Problema con ID " + idProblema + " no encontrado");
        }
        problemaRepository.deleteById(idProblema);
    }

    // Estudiante methods
    @Override
    public ProblemaDtoResponse saveProblema(Long idEst, ProblemaDtoRequest dto) {
        Estudiante est = estudianteRepository.findById(idEst)
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante con ID " + idEst + " no encontrado"));
        Problema nueva = mapper.toEntity(dto);
        var esp = espacioRepository.findById(dto.espacioId())
                .orElseThrow(() -> new EspacioNotFoundException("Espacio con ID " + dto.espacioId() + " no encontrado"));
        nueva.setEspacio(esp);
        nueva.setEstudiante(est);
        Problema saved = problemaRepository.save(nueva);
        return mapper.toResponse(saved);
    }

    @Override
    public void deleteProblemaEstudiante(Long idEst, Long idProblema) {
        Problema problema = problemaRepository.findById(idProblema)
                .orElseThrow(() -> new ProblemaNotFoundException("Problema con ID " + idProblema + " no encontrado"));
        if (!problema.getEstudiante().getIdEstudiante().equals(idEst)) {
            throw new SecurityException("No autorizado para eliminar este problema");
        }
        problemaRepository.deleteById(idProblema);
    }
}
