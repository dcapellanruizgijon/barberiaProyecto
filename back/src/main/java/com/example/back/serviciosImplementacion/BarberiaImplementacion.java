package com.example.back.serviciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.Barberia;
import com.example.back.repositorios.barberiaRepositorio;
import com.example.back.servicios.BarberiaService;

@Service
public class BarberiaImplementacion implements BarberiaService{
    
    @Autowired
    private barberiaRepositorio repo;

    @Override
    public List<Barberia> getAllBarberias() {
        return repo.findAll();
    }   

    @Override
    public Barberia getBarberiaById(Long id) {
        return repo.findById(id).orElse(null);  
    }

    @Override
    public Barberia crearBarberia(Barberia barberia) {
        return repo.save(barberia); 
    }

    @Override
    public Barberia actualizarBarberia(Long id, Barberia barberia) {
        Barberia existingBarberia = repo.findById(id).orElse(null);
        if (existingBarberia != null) {
            // Actualizar todos los campos
            if (barberia.getNombre() != null) {
                existingBarberia.setNombre(barberia.getNombre());
            }
            if (barberia.getUbicacion() != null) {
                existingBarberia.setUbicacion(barberia.getUbicacion());
            }
            if (barberia.getLocalidad() != null) {
                existingBarberia.setLocalidad(barberia.getLocalidad());
            }
            if (barberia.getTelefono() != null) {
                existingBarberia.setTelefono(barberia.getTelefono());
            }
            // nuevos campos
            if (barberia.getLatitud() != null) {
                existingBarberia.setLatitud(barberia.getLatitud());
            }
            if (barberia.getLongitud() != null) {
                existingBarberia.setLongitud(barberia.getLongitud());
            }
            if (barberia.getLogo() != null) {
                existingBarberia.setLogo(barberia.getLogo());
            }
            if (barberia.getHeader() != null) {
                existingBarberia.setHeader(barberia.getHeader());
            }
            
            return repo.save(existingBarberia);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarBarberia(Long id) {
        repo.deleteById(id);    
    }

    @Override
    public List<Barberia> getBarberiaByNombre(String termino) {
        return repo.buscarPorNombreOLocalidad(termino);
    }
}