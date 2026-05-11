package com.example.back.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.Barberia;
import com.example.back.Barbero;
import com.example.back.SetupBarberiaDTO;
import com.example.back.servicios.BarberiaService;
import com.example.back.servicios.BarberoService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private BarberiaService barberiaService;

    @Autowired
    private BarberoService barberoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional // Si falla el barbero, no se crea la barbería (integridad)
    @PostMapping("/setup-barberia")
    public ResponseEntity<?> setupBarberia(@RequestBody SetupBarberiaDTO dto) {
        // 1. Crear y guardar la Barbería con todos tus campos
        Barberia barberia = new Barberia();
        barberia.setNombre(dto.getNombre());
        barberia.setUbicacion(dto.getUbicacion());
        barberia.setLocalidad(dto.getLocalidad());
        barberia.setTelefono(dto.getTelefono());
        barberia.setLogo("https://res.cloudinary.com/dwjgugsb4/image/upload/v1778487157/logoDefault_ti7slb.png");
        barberia.setHeader("https://res.cloudinary.com/dwjgugsb4/image/upload/v1778487157/headerDefault_uax3vn.png");
        barberia.setLatitud(dto.getLatitud());
        barberia.setLongitud(dto.getLongitud());

        Barberia nuevaBarberia = barberiaService.crearBarberia(barberia);

        // 2. Crear el Barbero Jefe vinculado a la barbería creada
        Barbero barbero = new Barbero();
        barbero.setNombre(dto.getNombreBarbero());
        barbero.setEmail(dto.getEmailBarbero());
        // Usamos el passwordEncoder para encriptar antes de guardar
        barbero.setContrasena(passwordEncoder.encode(dto.getContrasenaBarbero()));
        barbero.setImg("https://res.cloudinary.com/dwjgugsb4/image/upload/v1778147454/dsx8oimeuzoj00o6yrqu.png");
        barbero.setBarberiaId(nuevaBarberia.getId());

        Barbero nuevoBarbero = barberoService.guardar(barbero);

        // 3. ACTUALIZACIÓN CRÍTICA: Guardar el ID del barbero en la barbería
        // Ya que tu entidad Barberia tiene un campo 'barberoId'
        nuevaBarberia.setBarberoId(nuevoBarbero.getId());
        barberiaService.crearBarberia(nuevaBarberia);

        return ResponseEntity.ok("Barbería '" + nuevaBarberia.getNombre() + "' desplegada con éxito");
    }
}
