package com.example.back.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.Incidencia;
import com.example.back.repositorios.IncidenciaRepository;

@Service
public class IncidenciasService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public List<Incidencia> getIncidencias() {
        return incidenciaRepository.findAllByOrderByIdDesc();
    }
    

}
