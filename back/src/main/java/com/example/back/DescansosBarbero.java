package com.example.back;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "descansos_barbero")
@Data @AllArgsConstructor @NoArgsConstructor
public class DescansosBarbero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barbero_id", nullable = false)
    private Barbero barbero;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    private String motivo; // "Vacaciones", "Baja", etc.
}