package edu.unimagdalena.reservasespacios.services.impl;

import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioSaveDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.requests.horarioespacio.HorarioEspacioUpdateEstadoDto;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.HorarioEspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioEspacioServiceImpl implements HorarioEspacioService {


    @Override
    public HorarioEspacioDtoResponse saveHorarioEspacio(HorarioEspacioSaveDtoRequest horarioDto) {
        return null;
    }

    @Override
    public List<HorarioEspacioDtoResponse> findAllHorariosEspacios() {
        return List.of();
    }

    @Override
    public HorarioEspacioDtoResponse findHorarioEspacioById(Long id) {
        return null;
    }

    @Override
    public HorarioEspacioDtoResponse updateHorarioEspacio(Long id, HorarioEspacioDtoRequest horarioDto) {
        return null;
    }

    @Override
    public HorarioEspacioDtoResponse updateEstadoHorarioEspacio(Long id, HorarioEspacioUpdateEstadoDto estadoEspacio) {
        return null;
    }

    @Override
    public void deleteHorarioEspacio(Long id) {

    }
}
