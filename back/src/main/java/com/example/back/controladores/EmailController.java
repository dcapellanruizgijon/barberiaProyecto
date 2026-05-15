package com.example.back.controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.Barbero;
import com.example.back.Cliente;
import com.example.back.repositorios.BarberoRepositorio;
import com.example.back.repositorios.ClienteRepositorio;
import com.example.back.servicios.EmailService;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private ClienteRepositorio clienteRepository;

    @Autowired
    private BarberoRepositorio barberoRepository; // <--- TE FALTABA ESTO (inyectarlo)

    @Autowired
    private EmailService emailService;

// 1. Correo de Bienvenida (Se llama tras un registro con éxito)
    @PostMapping("/bienvenida")
    public ResponseEntity<String> enviarBienvenida(@RequestParam String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            emailService.enviarEmailBienvenida(cliente.getEmail(), cliente.getNombre());
            return ResponseEntity.ok("Email de bienvenida enviado.");
        }
        return ResponseEntity.badRequest().body("Usuario no encontrado.");
    }

    @PostMapping("/confirmar-cita")
    public ResponseEntity<String> confirmarCita(@RequestBody Map<String, String> infoCita) {
        // Extraemos los datos que vienen del Front o del Service
        String email = infoCita.get("email");
        String nombre = infoCita.get("nombre");
        String barbero = infoCita.get("barbero");
        String fecha = infoCita.get("fecha");
        String hora = infoCita.get("hora");

        emailService.enviarEmailConfirmacionCita(email, nombre, barbero, fecha, hora);
        return ResponseEntity.ok("Notificación de cita enviada.");
    }

    @PostMapping("/incidencia-resuelta")
    public ResponseEntity<String> incidenciaResuelta(@RequestBody Map<String, String> infoIncidencia) {
        String email = infoIncidencia.get("email");
        String nombre = infoIncidencia.get("nombre");
        String tituloIncidencia = infoIncidencia.get("titulo");
        String comentarioAdmin = infoIncidencia.get("comentario");

        // Necesitamos crear este método en el EmailService (te lo pongo abajo)
        emailService.enviarEmailIncidenciaResuelta(email, nombre, tituloIncidencia, comentarioAdmin);
        return ResponseEntity.ok("Notificación de incidencia enviada.");
    }

    @PostMapping("/recuperar-password")
    public ResponseEntity<String> solicitarRecuperacion(@RequestParam String email) {
        String nombreUsuario = null;

        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            nombreUsuario = cliente.getNombre();
        } else {
            Barbero barbero = barberoRepository.findByEmail(email);
            if (barbero != null) {
                nombreUsuario = barbero.getNombre();
            }
        }

        if (nombreUsuario != null) {
            // CAMBIO: Enviamos el email en la URL para que el Front lo reconozca
            String enlace = "http://localhost:4200/reset-password?email=" + email;
            emailService.enviarEmailRecuperacion(email, nombreUsuario, enlace);
        }

        return ResponseEntity.ok("Si el correo electrónico está registrado, recibirás un enlace pronto.");
    }

    @PostMapping("/solicitar-barberia")
    public ResponseEntity<String> solicitarBarberia(@RequestBody Map<String, String> datos) {
        emailService.enviarNotificacionNuevaBarberia(
                datos.get("nombre"),
                datos.get("ubicacion"),
                datos.get("localidad"),
                datos.get("telefono"),
                datos.get("responsable"),
                datos.get("email")
        );
        return ResponseEntity.ok("Solicitud enviada al administrador.");
    }
}
