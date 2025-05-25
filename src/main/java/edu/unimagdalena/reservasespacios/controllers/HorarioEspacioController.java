package edu.unimagdalena.reservasespacios.controllers;

import edu.unimagdalena.reservasespacios.dtos.requests.HorarioEspacioDtoRequest;
import edu.unimagdalena.reservasespacios.dtos.response.HorarioEspacioDtoResponse;
import edu.unimagdalena.reservasespacios.services.interfaces.HorarioEspacioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/horarios-espacios")
public class HorarioEspacioController {

    private final HorarioEspacioService horarioEspacioService;

    @GetMapping
    public ResponseEntity<List<HorarioEspacioDtoResponse>> obtenerTodosHorariosServicios(){
        return ResponseEntity.ok(horarioEspacioService.findAllHorarioEspacio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioEspacioDtoResponse> obtenerHorarioEspacioPorId(@PathVariable
                                                                                    @NotNull(message = "El id no puede ser nulo")
                                                                                    @Positive(message = "El id debe ser positivo")
                                                                                    Long id){

        return ResponseEntity.ok(horarioEspacioService.findHorarioEspacioById(id));
    }

    @GetMapping("/por-dia")
    public ResponseEntity<List<HorarioEspacioDtoResponse>> obtenerHorarioEspacioPorDia(@RequestParam DayOfWeek dia){
        return ResponseEntity.ok(horarioEspacioService.findHorariosPorDia(dia));
    }

    @GetMapping("/por-espacio/{id}")
    public ResponseEntity<List<HorarioEspacioDtoResponse>> obtenerHorarioEspacioPorEspacio(@PathVariable
                                                                                               @NotNull(message = "El id no puede ser nulo")
                                                                                               @Positive(message = "El id debe ser positivo")
                                                                                               Long id){

        return ResponseEntity.ok(horarioEspacioService.findHorariosPorEspacio(id));
    }

    @GetMapping("/por-espacio-y-dia")
    public ResponseEntity<List<HorarioEspacioDtoResponse>> obtenerHorarioEspacioPorEspacioYDia(@RequestParam DayOfWeek dia,
                                                                                                @RequestParam Long idEspacio) {

        return ResponseEntity.ok(horarioEspacioService.findHorarioPorEspacioYDia(dia, idEspacio));
    }

    @PostMapping
    public ResponseEntity<HorarioEspacioDtoResponse> guardarHorarioEspacio(@RequestBody @Valid
                                                                           HorarioEspacioDtoRequest dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(horarioEspacioService.saveHorarioEspacio(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioEspacioDtoResponse> actualizarHorarioEspacio(@PathVariable
                                                                                  @NotNull(message = "El id no puede ser nulo")
                                                                                  @Positive(message = "El id debe ser positivo")
                                                                                  Long id,
                                                                              @RequestBody @Valid HorarioEspacioDtoRequest dto){

        return ResponseEntity.ok(horarioEspacioService.updateHorarioEspacio(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarHorarioEspacio(@PathVariable
                                                         @NotNull(message = "El id no puede ser nulo")
                                                         @Positive(message = "El id debe ser positivo")
                                                         Long id){

        horarioEspacioService.deleteHorarioEspacio(id);
        return ResponseEntity.noContent().build();
    }

}
