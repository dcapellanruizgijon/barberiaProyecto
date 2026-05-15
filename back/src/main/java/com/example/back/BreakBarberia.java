package com.example.back;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"breaks", "barberos", "servicios", "resenas", "hibernateLazyInitializer"})
    private Barberia barberia;

    private Integer diaSemana;

    @Column(nullable = false)
    private String horaInicio;

    @Column(nullable = false)
    private String horaFin;

    private String descripcion;
}