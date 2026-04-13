package com.example.back.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Cita;


public interface CitaRepositorio extends JpaRepository<Cita, Integer>{
    
}
