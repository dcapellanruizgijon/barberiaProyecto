// DisponibilidadService.java
package com.example.back.serviciosImplementacion;
import com.example.back.*;
import com.example.back.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DisponibilidadService {

    @Autowired private HorarioBarberiaRepository horarioRepo;
    @Autowired private DescansoBarberoRepository descansoRepo;
    @Autowired private BreakBarberiaRepository breakRepo;
    @Autowired private CitaRepositorio citaRepo; // el que ya tienes

    private static final List<String> HORAS_BASE = List.of(
        "09:00","09:30","10:00","10:30","11:00","11:30",
        "12:00","12:30","16:00","16:30","17:00","17:30","18:00","18:30"
    );

    public DisponibilidadDiaDTO calcularDisponibilidad(Long barberiaId, Long barberoId, String fechaStr) {

        LocalDate fecha = LocalDate.parse(fechaStr);
        // DayOfWeek: MONDAY=1 ... SUNDAY=7, lo convertimos a 0=Lun...6=Dom
        int diaSemana = fecha.getDayOfWeek().getValue() - 1;

        DisponibilidadDiaDTO resultado = new DisponibilidadDiaDTO();

        // 1. ¿Está abierta la barbería ese día de la semana?
        List<HorarioBarberia> horarios = horarioRepo.findByBarberiaIdAndActivoTrue(barberiaId);
        Optional<HorarioBarberia> horarioDelDia = horarios.stream()
            .filter(h -> h.getDiaSemana() == diaSemana)
            .findFirst();

        if (horarioDelDia.isEmpty()) {
            resultado.setDiaAbierto(false);
            resultado.setHorasDisponibles(Collections.emptyList());
            resultado.setHorasOcupadas(Collections.emptyList());
            resultado.setHorasEnBreak(Collections.emptyList());
            return resultado;
        }

        resultado.setDiaAbierto(true);
        HorarioBarberia horario = horarioDelDia.get();

        // 2. ¿El barbero descansa ese día?
        List<DescansosBarbero> descansos = descansoRepo.findDescansosActivosEnFecha(barberoId, fecha);
        resultado.setBarberoDescansa(!descansos.isEmpty());

        // 3. Horas dentro del horario de apertura
        List<String> horasDentroDeHorario = HORAS_BASE.stream()
            .filter(h -> horaEstaEnRango(h, horario.getHoraApertura(), horario.getHoraCierre()))
            .collect(Collectors.toList());

        // 4. Horas en break
        List<BreakBarberia> breaks = breakRepo.findByBarberiaId(barberiaId).stream()
            .filter(b -> b.getDiaSemana() == null || b.getDiaSemana() == diaSemana)
            .collect(Collectors.toList());

        List<String> horasEnBreak = horasDentroDeHorario.stream()
            .filter(h -> breaks.stream().anyMatch(b -> horaEstaEnRango(h, b.getHoraInicio(), b.getHoraFin())))
            .collect(Collectors.toList());

        resultado.setHorasEnBreak(horasEnBreak);

        // 5. Horas ocupadas por citas existentes
        String fechaHoraPrefix = fechaStr + " ";
        List<String> horasOcupadas = citaRepo.findByBarberiaId(barberiaId).stream()
            .filter(c -> c.getFechaHora().startsWith(fechaHoraPrefix))
            .filter(c -> barberoId == null || 
                        (c.getBarberoId() != null && c.getBarberoId().getId().equals(barberoId)))
            .map(c -> c.getFechaHora().replace(fechaHoraPrefix, ""))
            .collect(Collectors.toList());

        resultado.setHorasOcupadas(horasOcupadas);

        // 6. Horas disponibles = dentro del horario, sin break, sin ocupar
        List<String> disponibles = horasDentroDeHorario.stream()
            .filter(h -> !horasEnBreak.contains(h) && !horasOcupadas.contains(h))
            .collect(Collectors.toList());

        resultado.setHorasDisponibles(disponibles);

        return resultado;
    }

    // Comprueba si "hora" (ej: "10:00") está dentro del rango [inicio, fin)
    private boolean horaEstaEnRango(String hora, String inicio, String fin) {
        LocalTime t = LocalTime.parse(hora);
        LocalTime tInicio = LocalTime.parse(inicio);
        LocalTime tFin = LocalTime.parse(fin);
        return !t.isBefore(tInicio) && t.isBefore(tFin);
    }
}