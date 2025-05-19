package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.ProblemaDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.ProblemaDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.ProblemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/problemas")
public class ProblemaController {

    private final ProblemaService problemaService;

    @PostMapping
    public ResponseEntity<ProblemaDtoResponse> crearProblema(@RequestBody @Valid ProblemaDtoRequest dto){
        ProblemaDtoResponse created = problemaService.saveProblema(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<ProblemaDtoResponse>> listarProblemas(){
        List<ProblemaDtoResponse> list = problemaService.findAllProblemas();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemaDtoResponse> obtenerProblema(@PathVariable("id") Long id){
        ProblemaDtoResponse resp = problemaService.findProblemaById(id);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProblemaDtoResponse> actualizarProblema(@PathVariable("id") Long id,
            @RequestBody @Valid ProblemaDtoRequest dto){
        ProblemaDtoResponse updated = problemaService.updateProblema(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProblema(@PathVariable("id") Long id){
        problemaService.deleteProblema(id);
        return ResponseEntity.noContent().build();
    }
}
