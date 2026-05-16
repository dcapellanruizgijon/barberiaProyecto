package com.example.back.servicios;

import java.time.LocalDate;
import java.util.List;

import com.example.back.Barberia;

public interface BarberiaService {
    public List<Barberia> getAllBarberias();
    public Barberia getBarberiaById(Long id);
    public Barberia crearBarberia(Barberia barberia);
    public Barberia actualizarBarberia(Long id, Barberia barberia);
    public void eliminarBarberia(Long id);
    public List<Barberia> getBarberiaByNombre(String nombre);

    Barberia actualizarEstado(Long id, boolean activa);
    Barberia actualizarFechaRenovacion(Long id, LocalDate nuevaFecha);
    // public List<Barberia> getBarberiasByBarberoId(Integer barberoId);
    // public List<Barberia> getBarberiasByLocalidad(String localidad);


    // Este se descomenta cuando tengamos el servicio de la clase Resenas
    // public List<Barberia> getBarberiasByValoracionMedia(Double valoracionMedia);
}
