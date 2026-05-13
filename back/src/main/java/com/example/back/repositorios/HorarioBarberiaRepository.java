// HorarioBarberiaRepository.java
package com.example.back.repositorios;

import com.example.back.HorarioBarberia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HorarioBarberiaRepository extends JpaRepository<HorarioBarberia, Long> {
    List<HorarioBarberia> findByBarberiaIdAndActivoTrue(Long barberiaId);
}