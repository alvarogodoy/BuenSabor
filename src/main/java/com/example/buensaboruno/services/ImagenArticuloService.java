package com.example.buensaboruno.services;

import com.example.buensaboruno.domain.entities.ImagenArticulo;
import com.example.buensaboruno.repositories.ImagenArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenArticuloService {

    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;

    public ImagenArticulo guardarImagen(ImagenArticulo imagenArticulo) {
        return imagenArticuloRepository.save(imagenArticulo);
    }

    public ImagenArticulo obtenerImagenPorId(Long id) {
        return imagenArticuloRepository.findById(id).orElse(null);
    }
}
