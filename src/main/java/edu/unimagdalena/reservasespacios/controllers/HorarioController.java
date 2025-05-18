package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.HorarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    @PostMapping
    public ResponseEntity<HorarioDtoResponse> saveHorario(@RequestBody @Valid HorarioDtoRequest horarioDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.saveHorario(horarioDtoRequest));
    }

    @GetMapping
    public ResponseEntity<List<HorarioDtoResponse>> findAllHorario(){
        return ResponseEntity.ok(horarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDtoResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(horarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDtoResponse> updateHorario(@PathVariable Long id, @RequestBody @Valid HorarioDtoRequest horarioDtoRequest){
        return ResponseEntity.ok(horarioService.updateHorario(id, horarioDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id){
        horarioService.deleteHorario(id);
        return ResponseEntity.noContent().build();
    }

}
