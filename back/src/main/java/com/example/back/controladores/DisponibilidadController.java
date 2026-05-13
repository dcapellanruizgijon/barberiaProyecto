package com.example.back.controladores;

import com.example.back.DisponibilidadDiaDTO;
import com.example.back.serviciosImplementacion.DisponibilidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disponibilidad")
@CrossOrigin(origins = "http://localhost:4200")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    @GetMapping
    public DisponibilidadDiaDTO getDisponibilidad(
        @RequestParam Long barberiaId,
        @RequestParam Long barberoId,
        @RequestParam String fecha
    ) {
        return disponibilidadService.calcularDisponibilidad(barberiaId, barberoId, fecha);
    }
}