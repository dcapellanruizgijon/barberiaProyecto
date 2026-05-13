package com.example.back;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "horarios_barberia")
@Data @AllArgsConstructor @NoArgsConstructor
public class HorarioBarberia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barberia_id", nullable = false)
    private Barberia barberia;

    // 0=Lunes, 1=Martes, ... 6=Domingo
    @Column(nullable = false)
    private Integer diaSemana;

    @Column(nullable = false)
    private String horaApertura; // "09:00"

    @Column(nullable = false)
    private String horaCierre;   // "18:00"

    private boolean activo = true;
}