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
            existingBarberia.setNombre(barberia.getNombre());
            existingBarberia.setUbicacion(barberia.getUbicacion());
            existingBarberia.setLocalidad(barberia.getLocalidad());
            existingBarberia.setTelefono(barberia.getTelefono());
            return repo.save(existingBarberia);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarBarberia(Long id) {
        repo.deleteById(id);    
    }

    // @Override
    // public List<Barberia> getBarberiaByNombre(String nombre) {
    //     List<Barberia> barberias = repo.findAll();
    //     return barberias.stream()
    //             .filter(b -> b.getNombre().toLowerCase().contains(nombre.toLowerCase()))
    //             .toList();
    // }
@Override
public List<Barberia> getBarberiaByNombre(String termino) {
    // La base de datos se encarga de mirar en AMBAS columnas
    // gracias al OR que pusimos en el Repository.
    return repo.buscarPorNombreOLocalidad(termino);
}
    // Para cuando tengamos el servicio de la clase Resenas
    // @Override
    // public List<Barberia> getBarberiasByValoracionMedia(Double valoracionMedia) {
    //     List<Barberia> barberias = repo.findAll();
    //     return barberias.stream()
    //             .filter(b -> b.getValoracionMedia() != null && b.getValoracionMedia() >= valoracionMedia)
    //             .toList();  
    // }

    

    
}
