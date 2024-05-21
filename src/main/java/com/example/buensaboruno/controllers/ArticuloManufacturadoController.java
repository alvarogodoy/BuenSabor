package com.example.buensaboruno.controllers;

import com.example.buensaboruno.domain.entities.*;
import com.example.buensaboruno.repositories.ArticuloManufacturadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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

    @PatchMapping("/articulos-manufacturados/{id}/recuperar")
    public void recuperarArticuloManufacturado(@PathVariable Long id) {
        ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticuloManufacturado no encontrado con id: " + id));

        articuloManufacturado.setEliminado(false);

        articuloManufacturadoRepository.save(articuloManufacturado);
    }

    @PutMapping("/articulos-manufacturados/{id}")
    public ArticuloManufacturado actualizarArticuloManufacturado(@PathVariable Long id, @RequestBody ArticuloManufacturado datosActualizados) {
        // Lógica para actualizar un ArticuloManufacturado por su ID con los datos proporcionados
        ArticuloManufacturado articulo = articuloManufacturadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticuloManufacturado no encontrado con id: " + id));
        articulo.setDenominacion(datosActualizados.getDenominacion());
        articulo.setPrecioVenta(datosActualizados.getPrecioVenta());
        articulo.setImagenesArticulo(datosActualizados.getImagenesArticulo());
        articulo.setUnidadMedida(datosActualizados.getUnidadMedida());
        articulo.setPreparacion(datosActualizados.getPreparacion());
        articulo.setDescripcion(datosActualizados.getDescripcion());
        articulo.setTiempoEstimadoMinutos(datosActualizados.getTiempoEstimadoMinutos());
        articulo.setArticuloManufacturadoDetalles(datosActualizados.getArticuloManufacturadoDetalles());
        articulo.setCategoria(datosActualizados.getCategoria());
        // Actualiza otros atributos según sea necesario
        return articuloManufacturadoRepository.save(articulo);
    }

}
