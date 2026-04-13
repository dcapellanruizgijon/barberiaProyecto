package com.example.back.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Barberia;

public interface barberiaRepositorio extends JpaRepository<Barberia, Integer>{
    
}
