package com.example.back.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.Resenas;
import com.example.back.servicios.ResenasService;

@RestController
@RequestMapping("/api/resenas")
@CrossOrigin(origins = "http://localhost:4200")
public class ResenasController {

    @Autowired
    private ResenasService service;

    @GetMapping
    public List<Resenas> obtenerTodas() {
        return service.listarTodas();
    }

    @GetMapping("/barberia/{idBarberia}")
    public List<Resenas> obtenerPorBarberia(@PathVariable Long idBarberia) {
        return service.buscarPorBarberia(idBarberia);
    }

    @PostMapping
    public Resenas crear(@RequestBody Resenas resena) {
        return service.guardar(resena);
    }
}
