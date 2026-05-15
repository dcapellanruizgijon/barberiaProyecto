// DisponibilidadDiaDTO.java
package com.example.back;

import lombok.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class DisponibilidadDiaDTO {

    private boolean diaAbierto;
    private String horaApertura;  
    private String horaCierre;    
    private List<String> horasDisponibles;
    private List<String> horasOcupadas;
    private List<String> horasEnBreak;
    private boolean barberoDescansa;
}