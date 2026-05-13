package com.example.back.controladores;

import com.example.back.BreakBarberia;
import com.example.back.DescansosBarbero;
import com.example.back.HorarioBarberia;
import com.example.back.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "http://localhost:4200")
public class HorarioController {

    @Autowired private HorarioBarberiaRepository horarioRepo;
    @Autowired private DescansoBarberoRepository descansoRepo;
    @Autowired private BreakBarberiaRepository breakRepo;

    // --- HORARIO SEMANAL ---
    @GetMapping("/barberia/{id}")
    public List<HorarioBarberia> getHorarios(@PathVariable Long id) {
        return horarioRepo.findByBarberiaIdAndActivoTrue(id);
    }

    @PostMapping("/barberia")
    public HorarioBarberia crearHorario(@RequestBody HorarioBarberia horario) {
        return horarioRepo.save(horario);
    }

    @DeleteMapping("/barberia/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable Long id) {
        horarioRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- DESCANSOS BARBERO ---
    @GetMapping("/descansos/barbero/{barberoId}")
    public List<DescansosBarbero> getDescansos(@PathVariable Long barberoId) {
        return descansoRepo.findByBarberoId(barberoId);
    }

    @PostMapping("/descansos")
    public DescansosBarbero crearDescanso(@RequestBody DescansosBarbero descanso) {
        return descansoRepo.save(descanso);
    }

    @DeleteMapping("/descansos/{id}")
    public ResponseEntity<Void> eliminarDescanso(@PathVariable Long id) {
        descansoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- BREAKS ---
    @GetMapping("/breaks/barberia/{barberiaId}")
    public List<BreakBarberia> getBreaks(@PathVariable Long barberiaId) {
        return breakRepo.findByBarberiaId(barberiaId);
    }

    @PostMapping("/breaks")
    public BreakBarberia crearBreak(@RequestBody BreakBarberia br) {
        return breakRepo.save(br);
    }

    @DeleteMapping("/breaks/{id}")
    public ResponseEntity<Void> eliminarBreak(@PathVariable Long id) {
        breakRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}