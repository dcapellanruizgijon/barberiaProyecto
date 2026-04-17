package com.example.back.servicios;

import java.util.List;

import com.example.back.Imagenes;

public interface ImagenesService {
    List<Imagenes> listarTodas();
    List<Imagenes> buscarPorBarberia(Long barberiaId);
    Imagenes guardar(Imagenes imagen);
    void eliminar(Long id);
}
