package com.example.back.servicios;

import java.util.List;

import com.example.back.Barbero;

public interface BarberoService {
    List<Barbero> obtenerTodos();
    Barbero obtenerPorId(Long id);
    Barbero guardar(Barbero barbero);
    void eliminar(Long id);
    // Podrías añadir un método para validar credenciales (login)
}
