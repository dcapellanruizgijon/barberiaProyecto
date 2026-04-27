package com.example.back.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.Servicio;
import com.example.back.servicios.ServicioService;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicioController {

    @Autowired
    private ServicioService service;

    @GetMapping
    public List<Servicio> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Servicio crear(@RequestBody Servicio servicio) {
        return service.guardar(servicio);
    }

    @GetMapping("/barberia/{barberiaId}")
    public List<Servicio> getServiciosByBarberia(@PathVariable Long barberiaId) {
        return service.getServiciosByBarberiaId(barberiaId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}