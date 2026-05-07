package com.example.back.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Barbero;
import com.example.back.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
   Cliente findByEmail(String email);
}
