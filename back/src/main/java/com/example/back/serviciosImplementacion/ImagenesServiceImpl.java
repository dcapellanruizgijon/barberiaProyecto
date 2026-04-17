package com.example.back.serviciosImplementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.Imagenes;
import com.example.back.repositorios.ImagenesRepositorio;
import com.example.back.servicios.ImagenesService;

@Service
public class ImagenesServiceImpl implements ImagenesService {

    @Autowired
    private ImagenesRepositorio repository;

    @Override
    public List<Imagenes> listarTodas() {
        return repository.findAll();
    }

    @Override
    public List<Imagenes> buscarPorBarberia(Long barberiaId) {
        return repository.findByBarberiaId(barberiaId);
    }

    @Override
    public Imagenes guardar(Imagenes imagen) {
        return repository.save(imagen);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // @Override
    // public Imagenes guardar(Imagenes imagen) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    // }
}