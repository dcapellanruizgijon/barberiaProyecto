package com.example.back.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.Cita;
import com.example.back.EnumEstadoCita;
import com.example.back.repositorios.CitaRepositorio;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepositorio repository;

    @Override
    public List<Cita> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public Cita obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Cita> obtenerPorBarbero(Long barberoId) {
        return repository.findByBarberoId(barberoId);
    }

    @Override
    public Cita agendarCita(Cita cita) {
        // Aquí podrías validar si el barbero ya tiene una cita a esa hora
        return repository.save(cita);
    }

    @Override
    public Cita actualizarEstado(Long id, EnumEstadoCita estado) {
        Cita cita = repository.findById(id).orElseThrow();
        cita.setEstadoCita(estado);
        return repository.save(cita);
    }

    @Override
    public void cancelarCita(Long id) {
        repository.deleteById(id);
    }

    // @Override
    // public Cita agendarCita(Cita cita) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'agendarCita'");
    // }

    // @Override
    // public Cita actualizarEstado(Long id, EnumEstadoCita estado) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'actualizarEstado'");
    // }
}