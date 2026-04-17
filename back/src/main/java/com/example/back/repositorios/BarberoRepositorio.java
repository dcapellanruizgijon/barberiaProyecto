package com.example.back.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Barbero;

public interface BarberoRepositorio extends JpaRepository<Barbero, Long>{
    Optional<Barbero> findByEmail(String email);
}
