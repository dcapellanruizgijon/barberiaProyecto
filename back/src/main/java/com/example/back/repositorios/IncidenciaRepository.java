package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Incidencia;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    List<Incidencia> findByEstado(String estado);

    List<Incidencia> findAllByOrderByIdDesc();

    List<Incidencia> findByCreadorIdAndTipoUsuario(Long creadorId, String tipoUsuario);
}
