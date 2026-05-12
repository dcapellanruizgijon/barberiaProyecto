package com.example.back.serviciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.Resenas;
import com.example.back.repositorios.ResenasRepositorio;
import com.example.back.servicios.ResenasService;

@Service
public class ResenasServiceImpl implements ResenasService {

    @Autowired
    private ResenasRepositorio repository;

    @Override
    public List<Resenas> listarTodas() {
        return repository.findAll();
    }

    @Override
    public List<Resenas> buscarPorBarberia(Long idBarberia) {
        // Nota: El nombre del método en el repositorio debe coincidir con el campo "id_barberia"
        // Si el campo se llama id_barberia, JPA busca findByIdBarberia.
        return repository.findByIdBarberia(idBarberia);
    }

    @Override
    public Resenas guardar(Resenas resena) {
        return repository.save(resena);
    }


    // ResenasService.java - Añade este método
public Resenas actualizar(Long id, Resenas resenaActualizada) {
    return repository.findById(id).map(resena -> {
        resena.setPuntuacion(resenaActualizada.getPuntuacion());
        resena.setComentario(resenaActualizada.getComentario());
        return repository.save(resena);
    }).orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
}

@Override
// ResenasService.java
public void eliminar(Long id) {
    repository.deleteById(id);
}
}
