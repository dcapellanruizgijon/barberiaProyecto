package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Imagenes;

public interface ImagenesRepositorio extends JpaRepository<Imagenes, Long>{
    List<Imagenes> findByBarberiaId(Long barberiaId);
}
