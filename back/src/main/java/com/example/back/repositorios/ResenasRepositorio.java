package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Resenas;

public interface ResenasRepositorio extends JpaRepository<Resenas, Long>{
    List<Resenas> findByIdBarberia(Long idBarberia);
}
