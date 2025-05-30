package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.mappers.HorarioEspacioMapper;
import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import edu.unimagdalena.reservasespacios.entities.HorarioEspacio;
import edu.unimagdalena.reservasespacios.exceptions.HorarioSolapadoException;
import edu.unimagdalena.reservasespacios.exceptions.HorasConflictException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.EspacioNotFoundException;
import edu.unimagdalena.reservasespacios.exceptions.notFound.HorarioEspacioNotFoundException;
import edu.unimagdalena.reservasespacios.repositories.EspacioRepository;
import edu.unimagdalena.reservasespacios.repositories.HorarioEspacioRepository;
import edu.unimagdalena.reservasespacios.services.interfaces.HorarioEspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HorarioEspacioServiceImpl implements HorarioEspacioService {

    private final HorarioEspacioRepository horarioEspacioRepository;
    private final HorarioEspacioMapper horarioEspacioMapper;
    private final EspacioRepository espacioRepository;

    @Override
    public HorarioEspacioDtoResponse findHorarioEspacioById(Long id) {
        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(id)
                .orElseThrow(() -> new HorarioEspacioNotFoundException("Horario-espacio con ID: " +id+" no encontrado"));

        return horarioEspacioMapper.toDtoResponse(horarioEspacio);
    }

    @Override
    public List<HorarioEspacioDtoResponse> findAllHorarioEspacio() {
        return horarioEspacioRepository.findAll().stream()
                .map(horarioEspacioMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<HorarioEspacioDtoResponse> findHorariosPorDia(DayOfWeek dia) {
        return horarioEspacioRepository.findHorarioEspacioByDia(dia).stream()
                .map(horarioEspacioMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<HorarioEspacioDtoResponse> findHorariosPorEspacio(Long idEspacio) {
        return horarioEspacioRepository.findHorarioEspacioByEspacio_IdEspacio(idEspacio).stream()
                .map(horarioEspacioMapper::toDtoResponse)
                .toList();
    }

    @Override
    public List<HorarioEspacioDtoResponse> findHorarioPorEspacioYDia(DayOfWeek dia, Long idEspacio) {
        return horarioEspacioRepository.findHorarioEspaciosByDiaAndEspacio_IdEspacio(dia, idEspacio)
                .stream()
                .map(horarioEspacioMapper::toDtoResponse)
                .toList();
    }

    @Override
    public HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioDtoRequest horarioEspacioDto) {

        HorarioEspacio horarioEspacio = horarioEspacioMapper.toEntity(horarioEspacioDto);

        Espacio espacio = espacioRepository.findById(horarioEspacioDto.idEspacio())
                .orElseThrow(() -> new EspacioNotFoundException("Espacio con ID: " + horarioEspacioDto.idEspacio() + " no encontrado"));

        //Validar que la franja no esté mal ingresada (horaInicio > horaFin && horaInicio != horaFin)
        validarHora(horarioEspacioDto.horaInicio(), horarioEspacioDto.horaFin());

        //Validar que no exista ya un horarioEspacio con los mismos atributos (Franja horaria y día)
        validarSolapamiento(horarioEspacioDto.idEspacio(), horarioEspacioDto.dia(),
                horarioEspacioDto.horaInicio(), horarioEspacioDto.horaFin());

        horarioEspacio.setEspacio(espacio);
        espacio.getHorariosEspacio().add(horarioEspacio);

        return horarioEspacioMapper.toDtoResponse(horarioEspacioRepository.save(horarioEspacio));

    }

    @Override
    public HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioEspacioDto) {

        HorarioEspacio horarioEspacio = horarioEspacioRepository.findById(id)
                .orElseThrow(() ->  new HorarioEspacioNotFoundException("Horario-espacio con ID: "+ id + " no encontrado"));

        validarHora(horarioEspacioDto.horaInicio(), horarioEspacioDto.horaFin());

        //Validar que no exista ya un horarioEspacio con los mismos atributos (Franja horaria y día)
        validarSolapamientoUpdate(id, horarioEspacioDto.idEspacio(), horarioEspacioDto.dia(),
                horarioEspacioDto.horaInicio(), horarioEspacioDto.horaFin());

        horarioEspacioMapper.updateHorarioEspacioFromDto(horarioEspacioDto, horarioEspacio);

        return horarioEspacioMapper.toDtoResponse(horarioEspacioRepository.save(horarioEspacio));
    }

    @Override
    public void deleteHorarioEspacio(Long id) {

        if(!horarioEspacioRepository.existsById(id)){
            throw new HorarioEspacioNotFoundException("Horario-espacio con ID: "+ id + " no encontrado");
        }

        horarioEspacioRepository.deleteById(id);
    }

    private void validarHora(LocalTime horaInicio, LocalTime horaFin){
        if(horaFin.isBefore(horaInicio)
                || horaFin.equals(horaInicio)){
            throw new HorasConflictException(horaInicio, horaFin);
        }
    }

    private void validarSolapamiento(Long idEspacio, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin){
        List<HorarioEspacio> existentes = horarioEspacioRepository.findHorarioEspaciosByDiaAndEspacio_IdEspacio(dia, idEspacio);

        for (HorarioEspacio h : existentes) {
            if (!(horaFin.isBefore(h.getHoraInicio()) || horaInicio.isAfter(h.getHoraFin()))) {
                throw new HorarioSolapadoException("El horario se solapa con otro ya existente para el mismo espacio y día.");
            }
        }
    }

    private void validarSolapamientoUpdate(Long idActual, Long idEspacio, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin){
        List<HorarioEspacio> existentes = horarioEspacioRepository.findHorarioEspaciosByDiaAndEspacio_IdEspacio(dia, idEspacio);

        for (HorarioEspacio h : existentes) {
            if (!h.getIdHorarioEspacio().equals(idActual) &&
                    (!(horaFin.isBefore(h.getHoraInicio()) || horaInicio.isAfter(h.getHoraFin())))) {
                throw new HorarioSolapadoException("El horario se solapa con otro ya existente para el mismo espacio y día.");
            }
        }
    }
}
