package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.HorarioEspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioSaveDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioUpdateEstadoDto;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.enums.EstadoEspacio;
import edu.unimagdalena.reservasespacios.entities.Horario;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import edu.unimagdalena.reservasespacios.exceptions.HorarioSolapadoException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.HorarioEspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.HorarioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.HorarioEspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.HorarioRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.HorarioEspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioEspacioServiceImpl implements HorarioEspacioService {

    private final HorarioEspacioRepository horarioEspacioRepository;
    private final HorarioEspacioMapper horarioEspacioMapper;
    private final HorarioRepository horarioRepository;
    private final EspacioRepository espacioRepository;

    @Override
    public HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioSaveDtoRequest horarioDto) {

        Horario horario = horarioRepository.findById(horarioDto.idHorario())
                .orElseThrow(() -> new HorarioNotFoundException("Horario con ID: "+horarioDto.idHorario() + " no encontrado"));

        validarSolapamiento(horarioDto.idEspacio(), horario.getHoraInicio(), horario.getHoraFin(), horarioDto.fecha());

        Espacio espacio = espacioRepository.findById(horarioDto.idEspacio())
                .orElseThrow(() -> new EspacioNotFoundException("Espacio con ID: "+horarioDto.idEspacio() + " no encontrado"));

        HorarioEspacio horarioEspacio = horarioEspacioMapper.toEntitySave(horarioDto);
        horarioEspacio.setEspacio(espacio);
        horarioEspacio.setHorario(horario);
        horarioEspacio.setEstadoEspacio(EstadoEspacio.DISPONIBLE);

        return horarioEspacioMapper.toHorarioEspacioDtoResponse(horarioEspacioRepository.save(horarioEspacio));
    }

    @Override
    public List<HorarioEspacioDtoResponse> findAllHorariosEspacios() {
        return horarioEspacioRepository.findAll()
                .stream()
                .map(horarioEspacioMapper::toHorarioEspacioDtoResponse)
                .toList();
    }

    @Override
    public HorarioEspacioDtoResponse findHorarioEspacioById(Long id) {
        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(id)
                .orElseThrow(() -> new HorarioEspacioNotFoundException("Espacio-horario con ID: " + id + " no encontrado"));

        return horarioEspacioMapper.toHorarioEspacioDtoResponse(horarioEspacio);
    }

    @Override
    public List<HorarioEspacioDtoResponse> findHorarioDisponiblesFechaYHora(LocalDate fecha, Long idEspacio) {
        return horarioEspacioRepository.findHorariosDisponiblesPorFechaYEspacio(fecha, idEspacio)
                .stream()
                .map(horarioEspacioMapper::toHorarioEspacioDtoResponse)
                .toList();
    }

    @Override
    public HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioDto) {

        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(id)
                .orElseThrow(() -> new HorarioEspacioNotFoundException("Espacio-horario con ID: "+id+" no encontrado"));

        Horario horario = horarioRepository.findById(horarioDto.idHorario()).
                orElseThrow(() -> new HorarioNotFoundException("Horario con ID: "+horarioDto.idHorario() + " no encontrado"));

        validarSolapamiento(horarioDto.idEspacio(), horario.getHoraInicio(), horario.getHoraFin(), horarioDto.fecha());

        Espacio espacio = espacioRepository.findById(horarioDto.idEspacio())
                .orElseThrow(() -> new EspacioNotFoundException("Espacio con ID: "+horarioDto.idEspacio()+" no encontrado"));

        horarioEspacio.setHorario(horario);
        horarioEspacio.setEspacio(espacio);

        horarioEspacioMapper.updateHorarioEspacioFromDto(horarioDto, horarioEspacio);

        return horarioEspacioMapper.toHorarioEspacioDtoResponse(horarioEspacioRepository.save(horarioEspacio));

    }

    @Override
    public HorarioEspacioDtoResponse updateEstadoHorarioEspacio(Long id, HorarioEspacioUpdateEstadoDto estadoEspacio){

        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(id)
                .orElseThrow(() -> new HorarioEspacioNotFoundException("Espacio-horario con ID: "+id+" no encontrado"));

        horarioEspacio.setEstadoEspacio(estadoEspacio.estadoEspacio());

        return horarioEspacioMapper.toHorarioEspacioDtoResponse(horarioEspacioRepository.save(horarioEspacio));
    }


    @Override
    public void deleteHorarioEspacio(Long id) {
        if(!horarioEspacioRepository.existsById(id)){
            throw new HorarioEspacioNotFoundException("Horario-espacio con ID: " + id +" no encontrado");
        }

        horarioEspacioRepository.deleteById(id);
    }

    private void validarSolapamiento(Long idEspacio, LocalTime nHoraIni, LocalTime nHoraFin, LocalDate fecha){
        List<HorarioEspacio> horariosExistentes = horarioEspacioRepository.findHorariosExistentes(fecha, idEspacio);
        if(horariosExistentes.isEmpty()){
            return;
        }

        for (HorarioEspacio horarioEspacio : horariosExistentes){
            LocalTime horaIni = horarioEspacio.getHorario().getHoraInicio();
            LocalTime horaFin = horarioEspacio.getHorario().getHoraFin();

            if(nHoraIni.isBefore(horaFin) && nHoraFin.isAfter(horaIni)){
                throw new HorarioSolapadoException(idEspacio, nHoraIni, nHoraFin);
            }
        }
    }
}
