package com.example.back.controladores;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.back.servicios.CloudinaryService;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    private final CloudinaryService cloudinaryService;

    public FotoController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/subir")
    public String subir(@RequestParam("file") MultipartFile file) {
        // Llama a tu servicio y devuelve la URL de la imagen
        return cloudinaryService.subirImagen(file);
    }
}
