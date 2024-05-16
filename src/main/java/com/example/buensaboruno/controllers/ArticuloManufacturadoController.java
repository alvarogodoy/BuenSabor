package com.example.buensaboruno.controllers;

import com.example.buensaboruno.domain.entities.*;
import com.example.buensaboruno.repositories.ArticuloInsumoRepository;
import com.example.buensaboruno.repositories.ArticuloManufacturadoRepository;
import com.example.buensaboruno.repositories.ImagenArticuloRepository;
import com.example.buensaboruno.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ArticuloManufacturadoController{

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @GetMapping("/articulos-manufacturados")
    public List<ArticuloManufacturado> getAllArticulosManufacturados(){
        return articuloManufacturadoRepository.findAll();
    }

    @GetMapping("/articulos-manufacturados/{id}")
    public ArticuloManufacturado getArticuloManufacturadoById(@PathVariable long id){
        return articuloManufacturadoRepository.findById(id).orElse(null);
    }

    @PostMapping("/articulos-manufacturados")
    public ArticuloManufacturado crearArticuloManufacturado(@RequestBody ArticuloManufacturado nuevoArticulo) {
        return articuloManufacturadoRepository.save(nuevoArticulo);
    }

    @PatchMapping("/articulos-manufacturados/{id}")
    public void eliminarArticuloManufacturado(@PathVariable Long id) {
        // Lógica para eliminar lógicamente un ArticuloManufacturado por su ID
        ArticuloManufacturado articulo = articuloManufacturadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticuloManufacturado no encontrado con id: " + id));
        articulo.setEliminado(true);
        articuloManufacturadoRepository.save(articulo);
    }

    @PutMapping("/articulos-manufacturados/{id}")
    public ArticuloManufacturado actualizarArticuloManufacturado(@PathVariable Long id, @RequestBody ArticuloManufacturado datosActualizados) {
        // Lógica para actualizar un ArticuloManufacturado por su ID con los datos proporcionados
        ArticuloManufacturado articulo = articuloManufacturadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticuloManufacturado no encontrado con id: " + id));
        articulo.setDenominacion(datosActualizados.getDenominacion());
        // Actualiza otros atributos según sea necesario
        return articuloManufacturadoRepository.save(articulo);
    }

}
