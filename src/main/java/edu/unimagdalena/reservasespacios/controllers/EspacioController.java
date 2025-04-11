package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.services.implementss.EspacioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/espacios")
public class EspacioController {
    private final EspacioServiceImpl service;

    @PostMapping
    public ResponseEntity<EspacioDTOResponse> crerEspacio(@RequestBody EspacioDTOResquests espacioDto){
        return ResponseEntity.ok(service.crearEspacio(espacioDto));
    }



}
