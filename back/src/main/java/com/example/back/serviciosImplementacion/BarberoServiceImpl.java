package com.example.back.serviciosImplementacion;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.Barbero;
import com.example.back.repositorios.BarberoRepositorio;
import com.example.back.servicios.BarberoService;

@Service
public class BarberoServiceImpl implements BarberoService {

    @Autowired
    private BarberoRepositorio repository;

    @Override
    public List<Barbero> obtenerTodos() {
        return repository.findAll();
    }

    @Override
    public Barbero obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Barbero guardar(Barbero barbero) {
        return repository.save(barbero);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Barbero> obtenerPorBarberiaId(Long barberiaId) {
        return repository.findByBarberiaId(barberiaId);
    }

    @Override
    public Barbero login(String email, String contrasena) {
        List<Barbero> barberos = repository.findAll();
        for (Barbero barbero : barberos) {
            if (barbero.getEmail().equals(email) && barbero.getContrasena().equals(contrasena)) {
                return barbero; 
            } 
        }
        return null;
    }

    // @Override
    // public Barbero guardar(Barbero barbero) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    // }
}
