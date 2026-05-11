package com.example.back;

import lombok.Data;

@Data
public class SetupBarberiaDTO {
    // Datos completos de la Barbería
    private String nombre;
    private String ubicacion;
    private String localidad;
    private String telefono;
    private String logo;
    private String header;
    private Double latitud;
    private Double longitud;

    // Datos del Barbero Jefe
    private String nombreBarbero;
    private String emailBarbero;
    private String contrasenaBarbero; // Asegúrate de usar 'contrasena' para que coincida con tu entidad
    private String imgBarbero;
}