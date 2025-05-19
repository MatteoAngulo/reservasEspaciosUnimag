package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EspacioService {
    EspacioDTOResponse crearEspacio(EspacioDTOResquests espacioDTO);
    List<EspacioDTOResponse> findAllEspacios();
    EspacioDTOResponse findEspacioById(Long idEspacio);
    EspacioDTOResponse updateEspacio(Long idEspacio, EspacioDTOResquests espacioDTO);
    void deleteEspacio(Long idEspacio);
}
