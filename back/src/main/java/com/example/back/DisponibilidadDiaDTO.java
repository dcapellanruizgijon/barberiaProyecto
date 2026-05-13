// DisponibilidadDiaDTO.java
package com.example.back;

import lombok.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class DisponibilidadDiaDTO {

    private boolean diaAbierto;          // false si la barbería está cerrada ese día
    private List<String> horasDisponibles;
    private List<String> horasOcupadas;   // por citas ya reservadas
    private List<String> horasEnBreak;    // por breaks de la barbería
    private boolean barberoDescansa;      // true si el barbero está de vacaciones ese día
}