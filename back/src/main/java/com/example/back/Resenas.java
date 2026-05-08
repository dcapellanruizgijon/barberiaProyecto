package com.example.back;

import jakarta.persistence.Column;
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
@Table(name = "resenas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resenas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cambiamos Long por la Entidad Cliente
    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Cliente usuario;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_barberia")
    private Long idBarberia;
    
    private String comentario;
    private Integer puntuacion;
}
