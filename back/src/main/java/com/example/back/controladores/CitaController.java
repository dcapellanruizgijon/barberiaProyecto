package com.example.back.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
// 1. LISTAR TODAS (Solo para SuperAdmin, cuidado aquí)
    @GetMapping
    public List<Cita> listar() {
        return service.obtenerTodas();
    }

    // 2. BUSCAR POR ID
    @GetMapping("/{id}")
    public Cita buscar(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // 3. CITAS DE UN CLIENTE ESPECÍFICO (El que usa tu Perfil)
    @GetMapping("/cliente/{clienteId}")
    public List<Cita> listarPorCliente(@PathVariable Long clienteId) {
        System.out.println("Buscando citas para el cliente ID: " + clienteId);
        return service.obtenerPorCliente(clienteId);
    }

    // 4. CITAS DE UN BARBERO (El que usa el Admin-Dashboard)
    @GetMapping("/barbero/{barberoId}")
    public List<Cita> listarPorBarbero(@PathVariable Long barberoId) {
        return service.obtenerPorBarbero(barberoId);
    }

    @PostMapping
    public Cita crear(@RequestBody Cita cita) {
        return service.agendarCita(cita);
    }

    // 5. ACTUALIZAR ESTADO (El que limpia las comillas de Angular)
    @PutMapping("/{id}/estado")
    public ResponseEntity<Cita> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        String estadoLimpio = nuevoEstado.replace("\"", "").trim();
        EnumEstadoCita estadoEnum = EnumEstadoCita.valueOf(estadoLimpio);
        Cita citaActualizada = service.actualizarEstado(id, estadoEnum);
        return ResponseEntity.ok(citaActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.cancelarCita(id);
    }

    @GetMapping("/ocupadas")
    public List<Cita> obtenerOcupadas(@RequestParam Long barberiaId, @RequestParam String fecha) {
        return service.obtenerOcupadas(barberiaId, fecha);
    }
    @GetMapping("/barberia/{barberiaId}")
public List<Cita> listarPorBarberia(@PathVariable Long barberiaId) {
    System.out.println("Cargando citas para la barbería: " + barberiaId);
    return service.obtenerPorBarberia(barberiaId); // Usa el método que ya tienes en el Service
}
}
