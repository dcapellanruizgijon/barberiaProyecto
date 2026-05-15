package com.example.back.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.Barbero;
import com.example.back.servicios.BarberoService;

@RestController
@RequestMapping("/api/barberos")
@CrossOrigin(origins = "http://localhost:4200")
public class BarberoController {

    @Autowired
    private BarberoService service;

    // GET: Listar todos los barberos
    @GetMapping
    public List<Barbero> listar() {
        return service.obtenerTodos();
    }

    // GET: Obtener un barbero específico por ID
    @GetMapping("/{id}")
    public Barbero buscar(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // POST: Crear o actualizar un barbero
    @PostMapping
    public Barbero crear(@RequestBody Barbero barbero) {
        return service.guardar(barbero);
    }

    // DELETE: Eliminar un barbero
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // GET: Obtener barberos por barberiaId
    @GetMapping("/barberia/{barberiaId}")
    public List<Barbero> getBarberosByBarberia(@PathVariable Long barberiaId) {
        return service.obtenerPorBarberiaId(barberiaId);
    }

    // POST: Login de barbero
    //IMPORTANTE RESCATARLO CON REQUEST BODY Y UN MAP PARA OBTENER LOS DATOS DE EMAIL Y CONTRASEÑA QUE VIENEN EN EL CUERPO DE LA PETICION (POST)
    @PostMapping("/login")
    public ResponseEntity<Barbero> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String contrasena = credentials.get("contrasena");

        Barbero barbero = service.login(email, contrasena);
        if (barbero != null) {
            return ResponseEntity.ok(barbero);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{id}/email")
    public ResponseEntity<String> getEmailBarbero(@PathVariable Long id) {
        Barbero barbero = service.obtenerPorId(id);
        if (barbero != null) {
            return ResponseEntity.ok(barbero.getEmail());
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
}
