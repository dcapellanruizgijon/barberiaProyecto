package com.example.back.serviciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // ✅ AÑADIR
import org.springframework.stereotype.Service;

import com.example.back.Barbero;
import com.example.back.repositorios.BarberoRepositorio;
import com.example.back.servicios.BarberoService;

@Service
public class BarberoServiceImpl implements BarberoService {

    @Autowired
    private BarberoRepositorio repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; 

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
        // ✅ HASHEAR LA CONTRASEÑA ANTES DE GUARDAR
        if (barbero.getContrasena() != null && !barbero.getContrasena().startsWith("$2a$")) {
            String contrasenaHasheada = passwordEncoder.encode(barbero.getContrasena());
            barbero.setContrasena(contrasenaHasheada);
        }
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
    public Barbero login(String email, String contrasenaPlana) { 
        
        //  Buscar por email directamente (más eficiente que findAll)
        Barbero barbero = repository.findByEmail(email);
        
        if (barbero != null && passwordEncoder.matches(contrasenaPlana, barbero.getContrasena())) {
            return barbero;
        }
        
        return null;
    }
}