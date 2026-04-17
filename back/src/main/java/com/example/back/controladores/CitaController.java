package com.example.back.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.Cita;
import com.example.back.EnumEstadoCita;
import com.example.back.servicios.CitaService;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> listar() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Cita buscar(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/barbero/{barberoId}")
    public List<Cita> listarPorBarbero(@PathVariable Long barberoId) {
        return service.obtenerPorBarbero(barberoId);
    }

    @PostMapping
    public Cita crear(@RequestBody Cita cita) {
        return service.agendarCita(cita);
    }

    // Cambiar estado (confirmar, cancelar, etc)
    @PatchMapping("/{id}/estado")
    public Cita cambiarEstado(@PathVariable Long id, @RequestParam EnumEstadoCita estado) {
        return service.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.cancelarCita(id);
    }
}