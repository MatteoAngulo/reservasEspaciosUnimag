package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.SedeDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.SedeDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.SedeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    private final SedeService sedeService;

    @PostMapping
    public ResponseEntity<SedeDtoResponse> createSede(@RequestBody @Valid SedeDtoRequest dto) {
        SedeDtoResponse created = sedeService.saveSede(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<SedeDtoResponse>> getAllSedes() {
        List<SedeDtoResponse> list = sedeService.findAllSedes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDtoResponse> getSedeById(@PathVariable("id") Long id) {
        SedeDtoResponse sede = sedeService.findSedeById(id);
        return ResponseEntity.ok(sede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SedeDtoResponse> updateSede(
            @PathVariable("id") Long id,
            @RequestBody @Valid SedeDtoRequest dto) {
        SedeDtoResponse updated = sedeService.updateSede(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable("id") Long id) {
        sedeService.deleteSede(id);
        return ResponseEntity.noContent().build();
    }
}
