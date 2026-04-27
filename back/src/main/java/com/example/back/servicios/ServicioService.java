package com.example.back.servicios;

import java.util.List;

import com.example.back.Servicio;

public interface ServicioService {
    List<Servicio> listarTodos();
    Servicio guardar(Servicio servicio);
    void eliminar(Long id);
    List<Servicio> getServiciosByBarberiaId(Long barberiaId);
}