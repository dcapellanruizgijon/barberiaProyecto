package com.example.back;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; // Quitamos el "Id" del nombre de la variable

    @ManyToOne
    @JoinColumn(name = "barberia_id", nullable = false)
    private Barberia barberia; // <--- ESTO ES LO QUE TE FALTABA

    @ManyToOne
    @JoinColumn(name = "barbero_id", referencedColumnName = "id")
    private Barbero barberoId;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicioId;

    @Column(nullable = false)
    private String fechaHora; // Formato: "YYYY-MM-DD HH:MM"

    @Enumerated(EnumType.STRING) // Importante para que en la DB se guarde el texto (PENDIENTE, OK)
    private EnumEstadoCita estadoCita;

}
