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

import com.example.back.Incidencia;
import com.example.back.repositorios.IncidenciaRepository;
import com.example.back.servicios.IncidenciasService;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins = "http://localhost:4200") // Para que Angular pueda conectar
public class IncidenciasController {

    @Autowired
    private IncidenciasService incidenciasService;
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    // 1. POST: Para que cualquier usuario reporte un bug
    // URL: http://localhost:8080/api/incidencias
    @PostMapping("/crear") // <--- Ahora sí aceptará la ruta /api/incidencias/crear
    public Incidencia crearIncidencia(@RequestBody Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    @GetMapping("/usuario/{id}/{tipo}")
    public List<Incidencia> listarPorUsuario(@PathVariable Long id, @PathVariable String tipo) {
        // Aquí es donde llamamos al service corregido arriba
        return incidenciasService.getMisIncidencias(id, tipo);
    }

    // 2. GET: Para que el Admin vea todas las incidencias
    // URL: http://localhost:8080/api/incidencias
    @GetMapping
    public ResponseEntity<List<Incidencia>> listarTodas() {
        // El Service se encargará de pedirle al Repository las incidencias ordenadas
        List<Incidencia> lista = incidenciasService.getIncidencias();
        return ResponseEntity.ok(lista);
    }

    // 3. GET: Para filtrar por estado (ej: solo las PENDIENTES)
    // URL: http://localhost:8080/api/incidencias/estado/PENDIENTE
    @GetMapping("/estado/{estado}")
    public List<Incidencia> listarPorEstado(@PathVariable String estado) {
        return incidenciaRepository.findByEstado(estado);
    }

    // 4. PUT: Para que el Admin cambie el estado (ej: de PENDIENTE a RESUELTA)
    // URL: http://localhost:8080/api/incidencias/5/estado?nuevoEstado=RESUELTA
    @PutMapping("/{id}/gestionar")
    public Incidencia gestionarIncidencia(
            @PathVariable Long id,
            @RequestParam String nuevoEstado,
            @RequestBody(required = false) String comentario) {

        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        incidencia.setEstado(nuevoEstado);
        incidencia.setComentarioAdmin(comentario);

        return incidenciaRepository.save(incidencia);
    }

    // 5. DELETE: Por si el Admin quiere borrar reportes antiguos o falsos
    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        incidenciaRepository.deleteById(id);
    }

}
