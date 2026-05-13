// DescansoBarberoRepository.java
package com.example.back.repositorios;

import com.example.back.DescansosBarbero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface DescansoBarberoRepository extends JpaRepository<DescansosBarbero, Long> {

    List<DescansosBarbero> findByBarberoId(Long barberoId);

    // Devuelve los descansos activos para un barbero en una fecha concreta
    @Query("SELECT d FROM DescansosBarbero d WHERE d.barbero.id = :barberoId " +
           "AND :fecha BETWEEN d.fechaInicio AND d.fechaFin")
    List<DescansosBarbero> findDescansosActivosEnFecha(
        @Param("barberoId") Long barberoId,
        @Param("fecha") LocalDate fecha
    );
}