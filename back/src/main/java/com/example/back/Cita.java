package com.example.back;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Long usuarioId;
    
    @ManyToOne
    @JoinColumn(name = "barbero_id", referencedColumnName = "id")
    private Long barberoId;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Long servicioId;

    private String fechaHora; // Formato: "YYYY-MM-DD HH:MM"
    private EnumEstadoCita estadoCita; 

}
