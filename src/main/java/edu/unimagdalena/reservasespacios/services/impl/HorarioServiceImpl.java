package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.HorarioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.HorarioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Horario;
import edu.unimagdalena.reservasespacios.exceptions.notFound.HorarioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.HorarioRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private HorarioRepository horarioRepository;
    private HorarioMapper horarioMapper;

    @Override
    public HorarioDtoResponse findById(Long idHorario) {
        return horarioRepository.findById(idHorario)
                .map(horarioMapper::toHorarioDtoResponse)
                .orElseThrow(() -> new HorarioNotFoundException("Horario con ID: " + idHorario + " no encontrado"));
    }

    @Override
    public List<HorarioDtoResponse> findAll() {
        return horarioRepository.findAll().stream()
                .map(horarioMapper::toHorarioDtoResponse)
                .toList();
    }

    @Override
    public HorarioDtoResponse saveHorario(HorarioDtoRequest horarioDtoRequest) {
        Horario horario = horarioMapper.toEntity(horarioDtoRequest);
        return horarioMapper.toHorarioDtoResponse(horarioRepository.save(horario));
    }

    @Override
    public HorarioDtoResponse updateHorario(Long idHorario, HorarioDtoRequest horarioDtoRequest) {
        Horario horario = horarioRepository.findById(idHorario)
                .orElseThrow(() -> new HorarioNotFoundException("Horario con ID: " + idHorario + " no encontrado."));

        horario.setHoraInicio(horarioDtoRequest.horaInicio());
        horario.setHoraFin(horarioDtoRequest.horaFin());

        return horarioMapper.toHorarioDtoResponse(horarioRepository.save(horario));
    }

    @Override
    public void deleteHorario(Long idHorario) {

        if(!horarioRepository.existsById(idHorario)){
            throw new HorarioNotFoundException("Horario con ID: " +idHorario + " no encontrado");
        }

        horarioRepository.deleteById(idHorario);

    }
}
