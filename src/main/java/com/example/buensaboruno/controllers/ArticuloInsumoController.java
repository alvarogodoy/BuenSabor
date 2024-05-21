package com.example.buensaboruno.controllers;

import com.example.buensaboruno.domain.entities.ArticuloInsumo;
import com.example.buensaboruno.repositories.ArticuloInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ArticuloInsumoController {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @GetMapping("/insumos")
    public List<ArticuloInsumo> getAllArticuloInsumos() {
        return articuloInsumoRepository.findAll();
    }

    @GetMapping("/insumos/{id}")
    public ArticuloInsumo getArticuloInsumoById(@PathVariable long id) {
        return articuloInsumoRepository.findById(id).orElse(null);
    }

    @PostMapping("/insumos")
    public ArticuloInsumo crearArticuloInsumo(@RequestBody ArticuloInsumo nuevoInsumo) {
        return articuloInsumoRepository.save(nuevoInsumo);
    }

    @PatchMapping("/insumos/{id}/eliminar")
    public void eliminarArticuloInsumo(@PathVariable Long id) {
        ArticuloInsumo articuloInsumo = articuloInsumoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticuloInsumo no encontrado con id: " + id));
        articuloInsumo.setEliminado(true);
        articuloInsumoRepository.save(articuloInsumo);
    }

    @PatchMapping("/insumos/{id}/recuperar")
    public void recuperarArticuloInsumo(@PathVariable Long id) {
        ArticuloInsumo articuloInsumo = articuloInsumoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticuloInsumo no encontrado con id: " + id));

        articuloInsumo.setEliminado(false);

        articuloInsumoRepository.save(articuloInsumo);
    }

    @PutMapping("/insumos/{id}")
    public ArticuloInsumo actualizarArticuloInsumo(@PathVariable Long id, @RequestBody ArticuloInsumo datosActualizados) {
        ArticuloInsumo articuloInsumo = articuloInsumoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ArticuloInsumo no encontrado con id: " + id));
        articuloInsumo.setDenominacion(datosActualizados.getDenominacion());
        articuloInsumo.setPrecioVenta(datosActualizados.getPrecioVenta());
        articuloInsumo.setImagenesArticulo(datosActualizados.getImagenesArticulo());
        articuloInsumo.setUnidadMedida(datosActualizados.getUnidadMedida());
        articuloInsumo.setPrecioCompra(datosActualizados.getPrecioCompra());
        articuloInsumo.setStockActual(datosActualizados.getStockActual());
        articuloInsumo.setStockMaximo(datosActualizados.getStockMaximo());
        articuloInsumo.setEsParaElaborar(datosActualizados.getEsParaElaborar());
        articuloInsumo.setCategoria(datosActualizados.getCategoria());

        // Actualizar otros atributos según sea necesario
        return articuloInsumoRepository.save(articuloInsumo);
    }

}
