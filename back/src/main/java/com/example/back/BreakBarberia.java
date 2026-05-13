package com.example.back;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "breaks_barberia")
@Data @AllArgsConstructor @NoArgsConstructor
public class BreakBarberia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barberia_id", nullable = false)
    private Barberia barberia;

    // null = aplica a todos los días, o un día específico (0=Lun...6=Dom)
    private Integer diaSemana;

    @Column(nullable = false)
    private String horaInicio; // "14:00"

    @Column(nullable = false)
    private String horaFin;    // "16:00"

    private String descripcion; // "Comida"
}