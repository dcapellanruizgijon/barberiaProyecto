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
    @Autowired private CitaRepositorio citaRepo;

    public DisponibilidadDiaDTO calcularDisponibilidad(Long barberiaId, Long barberoId, String fechaStr) {

        LocalDate fecha = LocalDate.parse(fechaStr);
        int diaSemana = fecha.getDayOfWeek().getValue() - 1; // 0=Lun...6=Dom

        DisponibilidadDiaDTO resultado = new DisponibilidadDiaDTO();

        // 1. ¿Está abierta la barbería ese día?
        List<HorarioBarberia> horarios = horarioRepo.findByBarberiaIdAndActivoTrue(barberiaId);
        Optional<HorarioBarberia> horarioDelDia = horarios.stream()
            .filter(h -> h.getDiaSemana() == diaSemana)
            .findFirst();

        if (horarioDelDia.isEmpty()) {
            resultado.setDiaAbierto(false);
            resultado.setHoraApertura(null);
            resultado.setHoraCierre(null);
            resultado.setHorasOcupadas(Collections.emptyList());
            resultado.setHorasEnBreak(Collections.emptyList());
            resultado.setHorasDisponibles(Collections.emptyList());
            return resultado;
        }

        HorarioBarberia horario = horarioDelDia.get();
        resultado.setDiaAbierto(true);

        // 👇 Campos nuevos: Angular los usa para generar los slots según duración
        resultado.setHoraApertura(horario.getHoraApertura());
        resultado.setHoraCierre(horario.getHoraCierre());

        // 2. ¿El barbero descansa ese día?
        List<DescansosBarbero> descansos = descansoRepo.findDescansosActivosEnFecha(barberoId, fecha);
        resultado.setBarberoDescansa(!descansos.isEmpty());

        // 3. Breaks del día (los que aplican a todos los días o a este en concreto)
        List<BreakBarberia> breaksDelDia = breakRepo.findByBarberiaId(barberiaId).stream()
            .filter(b -> b.getDiaSemana() == null || b.getDiaSemana() == diaSemana)
            .collect(Collectors.toList());

        // Convertimos los breaks a rangos de string "HH:mm" para que Angular
        // pueda marcar como break cualquier slot que caiga dentro del rango
        List<String> horasEnBreak = breaksDelDia.stream()
            .map(b -> b.getHoraInicio() + "-" + b.getHoraFin())
            .collect(Collectors.toList());

        resultado.setHorasEnBreak(horasEnBreak);

        // 4. Horas ocupadas por citas existentes (solo la parte de hora "HH:mm")
        String fechaHoraPrefix = fechaStr + " ";
        List<String> horasOcupadas = citaRepo.findByBarberiaId(barberiaId).stream()
            .filter(c -> c.getFechaHora().startsWith(fechaHoraPrefix))
            .filter(c -> c.getBarberoId() != null &&
                         c.getBarberoId().getId().equals(barberoId))
            .map(c -> c.getFechaHora().replace(fechaHoraPrefix, ""))
            .collect(Collectors.toList());

        resultado.setHorasOcupadas(horasOcupadas);

        // 5. horasDisponibles lo dejamos vacío: Angular lo calcula con los slots
        resultado.setHorasDisponibles(Collections.emptyList());

        return resultado;
    }
}