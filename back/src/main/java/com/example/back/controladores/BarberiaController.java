package com.example.back.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.back.Barberia;
import com.example.back.servicios.BarberiaService;

@RestController
@RequestMapping("/api/barberias")
@CrossOrigin(origins = "*") // Permitir peticiones desde frontend
public class BarberiaController {

    @Autowired
    private BarberiaService barberiaService;

    // Obtener todas las barberías
    @GetMapping
    public ResponseEntity<List<Barberia>> getAllBarberias() {
        List<Barberia> barberias = barberiaService.getAllBarberias();
        return ResponseEntity.ok(barberias);
    }

    // Obtener barbería por ID
    @GetMapping("/{id}")
    public ResponseEntity<Barberia> getBarberiaById(@PathVariable Long id) {
        Barberia barberia = barberiaService.getBarberiaById(id);
        if (barberia != null) {
            return ResponseEntity.ok(barberia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar barberías por nombre 
    @GetMapping("/buscar")
    public ResponseEntity<List<Barberia>> getBarberiaByNombre(@RequestParam String nombre) {
        List<Barberia> barberias = barberiaService.getBarberiaByNombre(nombre);
        if (barberias != null && !barberias.isEmpty()) {
            return ResponseEntity.ok(barberias);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Crear nueva barbería
    @PostMapping
    public ResponseEntity<Barberia> crearBarberia(@RequestBody Barberia barberia) {
        Barberia nuevaBarberia = barberiaService.crearBarberia(barberia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaBarberia);
    }

    // Actualizar barbería completa
    @PutMapping("/{id}")
    public ResponseEntity<Barberia> actualizarBarberia(@PathVariable Long id, @RequestBody Barberia barberia) {
        Barberia barberiaActualizada = barberiaService.actualizarBarberia(id, barberia);
        if (barberiaActualizada != null) {
            return ResponseEntity.ok(barberiaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar barbería parcialmente 
    @PatchMapping("/{id}")
    public ResponseEntity<Barberia> actualizarBarberiaParcial(@PathVariable Long id, @RequestBody Barberia barberia) {
        Barberia existingBarberia = barberiaService.getBarberiaById(id);
        if (existingBarberia == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Actualizar solo los campos que vienen en la petición
        if (barberia.getNombre() != null) {
            existingBarberia.setNombre(barberia.getNombre());
        }
        if (barberia.getUbicacion() != null) {
            existingBarberia.setUbicacion(barberia.getUbicacion());
        }
        if (barberia.getLocalidad() != null) {
            existingBarberia.setLocalidad(barberia.getLocalidad());
        }
        if (barberia.getTelefono() != null) {
            existingBarberia.setTelefono(barberia.getTelefono());
        }
        
        Barberia barberiaActualizada = barberiaService.actualizarBarberia(id, existingBarberia);
        return ResponseEntity.ok(barberiaActualizada);
    }

    // Eliminar barbería
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBarberia(@PathVariable Long id) {
        Barberia barberia = barberiaService.getBarberiaById(id);
        if (barberia != null) {
            barberiaService.eliminarBarberia(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint preparado para cuando implementes la búsqueda por valoración media
    // @GetMapping("/valoracion")
    // public ResponseEntity<List<Barberia>> getBarberiasByValoracionMedia(@RequestParam Double valoracionMedia) {
    //     List<Barberia> barberias = barberiaService.getBarberiasByValoracionMedia(valoracionMedia);
    //     if (barberias != null && !barberias.isEmpty()) {
    //         return ResponseEntity.ok(barberias);
    //     } else {
    //         return ResponseEntity.noContent().build();
    //     }
    // }
}