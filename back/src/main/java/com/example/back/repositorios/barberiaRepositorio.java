package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.back.Barberia;

public interface barberiaRepositorio extends JpaRepository<Barberia, Long>{
    @Query("SELECT b FROM Barberia b WHERE " +
       "LOWER(b.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
       "LOWER(b.localidad) LIKE LOWER(CONCAT('%', :termino, '%'))")
List<Barberia> buscarPorNombreOLocalidad(@Param("termino") String termino);
}
