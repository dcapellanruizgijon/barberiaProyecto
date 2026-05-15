package com.example.back.servicios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    private final String BREVO_API_URL = "https://api.brevo.com/v3/smtp/email";
    private final String EMAIL_REMITENTE = "david.yerpes-gordillo@iesruizgijon.com";

    // 1. Email de Bienvenida
    @Async
    public void enviarEmailBienvenida(String destino, String nombreUsuario) {
        String contenido = "¡Gracias por registrarte en <strong>MasterCuts</strong>! Estamos encantados de tenerte con nosotros. Ya puedes empezar a reservar tus citas con nuestros barberos profesionales.";
        llamarApiBrevo(destino, nombreUsuario, "¡Bienvenido a MasterCuts!", contenido, "Ir a mi cuenta", null);
    }

    @Async
    public void enviarEmailIncidenciaResuelta(String destino, String nombreUsuario, String titulo, String comentario) {
        String contenido = "Nuestro equipo de soporte ha revisado tu reporte.<br><br>"
                + "<strong>Para ver la resolucion de su incidencia entra en la aplicacion.</strong><br><br>"
                + "Gracias por ayudarnos a mejorar MasterCuts. <br> Un cordial saludo.";
        llamarApiBrevo(destino, nombreUsuario, "Incidencia Resuelta - MasterCuts", contenido, "Ver mis reportes", null);
    }

    // 2. Email Confirmación de Cita
    @Async
    public void enviarEmailConfirmacionCita(String destino, String nombreUsuario, String barbero, String fecha, String hora) {
        String contenido = "Tu cita ha sido <strong>confirmada</strong> exitosamente.<br><br>"
                + "<strong>Barbero:</strong> " + barbero + "<br>"
                + "<strong>Fecha:</strong> " + fecha + "<br>"
                + "<strong>Hora:</strong> " + hora + "<br><br>"
                + "¡Te esperamos para darte el mejor servicio!";
        llamarApiBrevo(destino, nombreUsuario, "Confirmación de Cita - MasterCuts", contenido, "Ver mis citas", null);
    }

    // 3. Email Recuperar Contraseña
    @Async
    public void enviarEmailRecuperacion(String destino, String nombreUsuario, String enlaceRecuperacion) {
        String contenido = "Has solicitado restablecer tu contraseña. Haz clic en el botón de abajo para crear una nueva. Si no has sido tú, ignora este mensaje.";
        // CAMBIO: Ahora pasamos 'enlaceRecuperacion' en lugar de dejarlo fijo
        llamarApiBrevo(destino, nombreUsuario, "Recuperación de Contraseña", contenido, "Restablecer Contraseña", enlaceRecuperacion);
    }

    @Async
    public void enviarNotificacionNuevaBarberia(String nombreB, String ubicacion, String localidad, String telf, String responsable, String emailJefe) {
        String destinoAdmin = "david.yerpes-gordillo@iesruizgijon.com"; // <-- Pon AQUÍ el correo donde quieres recibir los avisos
        String asunto = "NUEVA SOLICITUD DE ALTA: " + nombreB;

        String contenido = "<h3>Nueva solicitud de colaboración</h3>"
                + "<p><strong>Barbería:</strong> " + nombreB + "</p>"
                + "<p><strong>Ubicación:</strong> " + ubicacion + " (" + localidad + ")</p>"
                + "<p><strong>Teléfono:</strong> " + telf + "</p>"
                + "<hr>"
                + "<p><strong>Responsable:</strong> " + responsable + "</p>"
                + "<p><strong>Email de contacto:</strong> " + emailJefe + "</p>"
                + "<p>Revisa los datos para proceder con el alta en el sistema.</p>";

        // Reutilizamos tu método privado enviando 'null' en el enlace de botón ya que es un aviso interno
        llamarApiBrevo(destinoAdmin, "Administrador MasterCuts", asunto, contenido, "Ver Dashboard", null);
    }

    private void llamarApiBrevo(String destino, String nombreDestino, String asunto, String contenido, String textoBoton, String urlBoton) {
        RestTemplate restTemplate = new RestTemplate();

        // Si urlBoton es nulo (para otros correos), mandamos a la home
        String href = (urlBoton != null) ? urlBoton : "http://localhost:4200";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        // --- DISEÑO SUPREME ACTUALIZADO ---
        String htmlLayout
                = "<html><body style='font-family: Arial, sans-serif; background-color: #1a1a1a; padding: 20px; color: #ffffff;'>"
                + "  <div style='max-width: 600px; margin: auto; background: #262626; padding: 30px; border-radius: 15px; border: 1px solid #d4af37;'>"
                + "    <h2 style='color: #d4af37; text-align: center; text-transform: uppercase;'>MasterCuts </h2>"
                + "    <hr style='border: 0; border-top: 1px solid #d4af37;'>"
                + "    <p style='font-size: 16px; color: #ffffff;'>Hola <strong>" + nombreDestino + "</strong>,</p>"
                + "    <p style='font-size: 16px; color: #cccccc; line-height: 1.6;'>" + contenido + "</p>"
                + "    <div style='text-align: center; margin-top: 35px;'>"
                // CAMBIO AQUÍ: Usamos la variable 'href' dinámica
                + "      <a href='" + href + "' style='background-color: #d4af37; color: #1a1a1a; padding: 14px 28px; text-decoration: none; border-radius: 8px; font-weight: bold; display: inline-block;'>" + textoBoton + "</a>"
                + "    </div>"
                + "    <p style='margin-top: 45px; font-size: 11px; color: #777777; text-align: center;'>Estilo y elegancia en cada corte.<br>Este es un mensaje automático, por favor no respondas.</p>"
                + "  </div>"
                + "</body></html>";

        Map<String, Object> body = new HashMap<>();

        Map<String, String> sender = new HashMap<>();
        sender.put("name", "MasterCuts");
        sender.put("email", EMAIL_REMITENTE);

        Map<String, String> to = new HashMap<>();
        to.put("email", destino);
        to.put("name", nombreDestino);

        body.put("sender", sender);
        body.put("to", Collections.singletonList(to));
        body.put("subject", asunto);
        body.put("htmlContent", htmlLayout);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(BREVO_API_URL, request, String.class);
        } catch (Exception e) {
            System.err.println("Error enviando correo vía Brevo: " + e.getMessage());
        }
    }
}
