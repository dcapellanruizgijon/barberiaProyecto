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

import com.example.back.Imagenes;
import com.example.back.servicios.ImagenesService;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "http://localhost:4200") // Permite que Angular acceda
public class ImagenesController {

    @Autowired
    private ImagenesService service;

    // GET: http://localhost:8080/api/imagenes
    @GetMapping
    public List<Imagenes> listar() {
        return service.listarTodas();
    }

    // GET: http://localhost:8080/api/imagenes/barberia/1
    @GetMapping("/barberia/{barberiaId}")
    public List<Imagenes> listarPorBarberia(@PathVariable Long barberiaId) {
        return service.buscarPorBarberia(barberiaId);
    }

    // POST: http://localhost:8080/api/imagenes
    @PostMapping
    public Imagenes crear(@RequestBody Imagenes imagen) {
        return service.guardar(imagen);
    }

    // DELETE: http://localhost:8080/api/imagenes/5
    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
