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

    /**
     * NUEVO: Para el Barbero: Ver solo sus propias incidencias. Importante: El
     * repositorio debe tener el método findByUsuarioId.
     */
    public List<Incidencia> getMisIncidencias(Long id, String tipoUsuario) {
        // IMPORTANTE: Pasamos los dos parámetros al repositorio
        return incidenciaRepository.findByCreadorIdAndTipoUsuario(id, tipoUsuario);
    }

    /**
     * Método para guardar o actualizar incidencias
     */
    public Incidencia guardar(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

}
