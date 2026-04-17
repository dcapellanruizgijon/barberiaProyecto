package com.example.back;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id_usuario;
    private Long id_barberia;
    private String comentario;
    private Integer puntuacion;
    
    //Si nos columpiamos mucho
    // private long imgUrl;
}
