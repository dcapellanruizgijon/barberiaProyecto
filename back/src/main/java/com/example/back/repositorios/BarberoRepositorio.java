package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Barbero;

public interface BarberoRepositorio extends JpaRepository<Barbero, Long>{
    Barbero findByEmail(String email);

    List<Barbero> findByBarberiaId(Long barberiaId);
    long countByBarberiaId(Long barberiaId);

}
