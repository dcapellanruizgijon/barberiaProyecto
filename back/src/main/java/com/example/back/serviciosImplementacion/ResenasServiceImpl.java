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
        // Si el campo se llama id_barberia, JPA busca findById_barberia o similar.
        return repository.findById_Barberia(idBarberia);
    }

    @Override
    public Resenas guardar(Resenas resena) {
        return repository.save(resena);
    }

    // @Override
    // public Resenas guardar(Resenas resena) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    // }
}
