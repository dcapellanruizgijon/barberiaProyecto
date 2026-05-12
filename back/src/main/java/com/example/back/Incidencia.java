package com.example.back;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "incidencias")
@Data
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Estados: PENDIENTE, EN_PROCESO, RESUELTA
    private String estado = "PENDIENTE";

    // Prioridad: BAJA, MEDIA, ALTA
    private String prioridad = "MEDIA";

    // Para saber quién la mandó
    @Column(name = "creador_id")
    private Long creadorId; 
    private String tipoUsuario; // "BARBERO" o "CLIENTE"

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String comentarioAdmin;
}
