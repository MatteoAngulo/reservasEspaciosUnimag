package edu.unimagdalena.reservasespacios.services.interfaces;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import org.springframework.stereotype.Service;

public interface EspacioService {
    EspacioDTOResponse crearEspacio(EspacioDTOResquests espacioDTO);

}
