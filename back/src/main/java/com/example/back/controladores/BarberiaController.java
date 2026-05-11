package com.example.back.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.Barberia;
import com.example.back.BarberiaStatsDTO;
import com.example.back.repositorios.BarberoRepositorio;
import com.example.back.repositorios.CitaRepositorio;
import com.example.back.servicios.BarberiaService;

@RestController
@RequestMapping("/api/barberias")
@CrossOrigin(origins = "*") // Permitir peticiones desde frontend
public class BarberiaController {

    @Autowired
    private BarberiaService barberiaService;

    @Autowired
    private CitaRepositorio citaRepository;

    @Autowired
    private BarberoRepositorio barberoRepository;

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
    // Actualizar barbería parcialmente 
@PatchMapping("/{id}")
public ResponseEntity<Barberia> actualizarBarberiaParcial(@PathVariable Long id, @RequestBody Barberia barberia) {
    System.out.println("=== PATCH RECIBIDO ===");
    System.out.println("ID: " + id);
    System.out.println("Logo recibido: " + barberia.getLogo());
    System.out.println("Header recibido: " + barberia.getHeader());
    System.out.println("Nombre recibido: " + barberia.getNombre());
    
    Barberia existingBarberia = barberiaService.getBarberiaById(id);
    if (existingBarberia == null) {
        return ResponseEntity.notFound().build();
    }

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
    if (barberia.getLatitud() != null) {
        existingBarberia.setLatitud(barberia.getLatitud());
    }
    if (barberia.getLongitud() != null) {
        existingBarberia.setLongitud(barberia.getLongitud());
    }
    if (barberia.getLogo() != null) {
        System.out.println("Actualizando LOGO a: " + barberia.getLogo());
        existingBarberia.setLogo(barberia.getLogo());
    }
    if (barberia.getHeader() != null) {
        System.out.println("Actualizando HEADER a: " + barberia.getHeader());
        existingBarberia.setHeader(barberia.getHeader());
    }
    
    Barberia barberiaActualizada = barberiaService.actualizarBarberia(id, existingBarberia);
    System.out.println("Barbería actualizada - Logo: " + barberiaActualizada.getLogo());
    System.out.println("Barbería actualizada - Header: " + barberiaActualizada.getHeader());
    
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

    @GetMapping("/{id}/stats")
    public ResponseEntity<BarberiaStatsDTO> getStats(@PathVariable Long id) {
        BarberiaStatsDTO stats = new BarberiaStatsDTO();

        stats.setCitasHoy(citaRepository.countCitasHoy(id));

        Double ingresos = citaRepository.sumIngresosHoy(id);
        stats.setIngresosHoy(ingresos != null ? ingresos : 0.0);

        stats.setBarberosActivos(barberoRepository.countByBarberiaId(id));
        stats.setProximaCita("17:00"); // Esto podrías calcularlo buscando la cita más cercana

        return ResponseEntity.ok(stats);
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
