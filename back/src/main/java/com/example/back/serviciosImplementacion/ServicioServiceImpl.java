package com.example.back.serviciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.Servicio;
import com.example.back.repositorios.ServicioRepositorio;
import com.example.back.servicios.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepositorio repository;

    @Override
    public List<Servicio> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Servicio guardar(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Servicio> getServiciosByBarberiaId(Long barberiaId) {
        return repository.findByBarberia_Id(barberiaId);
    }


    
    // @Override
    // public Servicio guardar(Servicio servicio) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    // }
}
