package com.example.back.servicios;

import java.util.List;

import com.example.back.Resenas;

public interface ResenasService {
    List<Resenas> listarTodas();
    List<Resenas> buscarPorBarberia(Long idBarberia);
    Resenas guardar(Resenas resena);
    Resenas actualizar(Long id, Resenas resenaActualizada);
    void eliminar(Long id);
}