package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.services.impl.EspacioServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/espacios")
public class EspacioController {
    private final EspacioServiceImpl service;

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EspacioDTOResponse> crerEspacio(@RequestBody EspacioDTOResquests espacioDto){
        return ResponseEntity.ok(service.crearEspacio(espacioDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<List<EspacioDTOResponse>> listarEspacios(){
        List<EspacioDTOResponse> list = service.findAllEspacios();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<EspacioDTOResponse> obtenerEspacio(@PathVariable
                                                                 @NotNull(message = "El id no puede estar vacio")
                                                                 @Positive(message = "El id debe ser positivo") Long id){
        EspacioDTOResponse esp = service.findEspacioById(id);
        return ResponseEntity.ok(esp);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EspacioDTOResponse> actualizarEspacio(@PathVariable
                                                                    @NotNull(message = "El id no puede estar vacio")
                                                                    @Positive(message = "El id debe ser positivo")Long id,
                                                                @RequestBody @Valid EspacioDTOResquests espacioDto){
        EspacioDTOResponse updated = service.updateEspacio(id, espacioDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> eliminarEspacio(@PathVariable
                                                    @NotNull(message = "El id no puede estar vacio")
                                                    @Positive(message = "El id debe ser positivo") Long id){
        service.deleteEspacio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sede/{sedeId}")
    @PreAuthorize("hasAnyRole('ESTUDIANTE','ADMINISTRADOR')")
    public ResponseEntity<List<EspacioDTOResponse>> listarPorSede(@PathVariable
                                                                      @NotNull(message = "El id no puede estar vacio")
                                                                      @Positive(message = "El id debe ser positivo") Long sedeId){
        List<EspacioDTOResponse> list = service.findEspaciosBySede(sedeId);
        return ResponseEntity.ok(list);
    }

}
