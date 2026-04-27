package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Servicio;

public interface ServicioRepositorio extends JpaRepository<Servicio, Long>{
// List<Servicio> findByNombreContaining(String nombre);
    List<Servicio> findByBarberia_Id(Long barberiaId);
}
