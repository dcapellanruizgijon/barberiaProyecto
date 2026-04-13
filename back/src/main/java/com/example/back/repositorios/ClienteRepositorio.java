package com.example.back.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
    
}
