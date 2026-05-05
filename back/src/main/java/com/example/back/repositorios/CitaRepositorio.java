package com.example.back.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.Cita;


public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    
    // Busca las citas de una barbería en un día específico
    // fechaHora formateada como "YYYY-MM-DD"
    List<Cita> findByBarberiaIdAndFechaHoraStartingWith(Long barberiaId, String fecha);

    // El que ya tenías pero corregido (sin el _Id si cambiaste el nombre de la variable)
    List<Cita> findByBarberoId(Long barberoId);

    List<Cita> findByClienteId(Long clienteId);
    List<Cita> findByBarberiaId(Long barberiaId);
}
