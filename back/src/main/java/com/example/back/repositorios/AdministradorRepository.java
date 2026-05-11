package com.example.back.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByEmail(String email);
}
