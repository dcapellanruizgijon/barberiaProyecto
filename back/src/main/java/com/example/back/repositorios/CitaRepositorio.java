package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Cita;


public interface CitaRepositorio extends JpaRepository<Cita, Long>{
    // Buscar citas por barbero para ver su agenda
    List<Cita> findByBarberoId(Long barberoId);
    
    // Buscar citas por cliente para su historial
    List<Cita> findByUsuarioId(Long clienteId);
}
