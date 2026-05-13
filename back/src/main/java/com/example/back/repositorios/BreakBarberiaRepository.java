// BreakBarberiaRepository.java
package com.example.back.repositorios;

import com.example.back.BreakBarberia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BreakBarberiaRepository extends JpaRepository<BreakBarberia, Long> {
    List<BreakBarberia> findByBarberiaId(Long barberiaId);
}