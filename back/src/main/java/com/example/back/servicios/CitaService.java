package com.example.back.servicios;

import java.util.List;

import com.example.back.Cita;
import com.example.back.EnumEstadoCita;

public interface CitaService {
    List<Cita> obtenerTodas();
    Cita obtenerPorId(Long id);
    List<Cita> obtenerPorBarbero(Long barberoId);
    Cita agendarCita(Cita cita);
    Cita actualizarEstado(Long id, EnumEstadoCita estado);
    void cancelarCita(Long id);
    List<Cita> obtenerOcupadas(Long barberiaId, String fecha);
    List<Cita> obtenerPorCliente(Long clienteId);
    List<Cita> obtenerPorBarberia(Long barberiaId);
}
