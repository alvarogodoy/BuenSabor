package com.example.buensaboruno.controllers;

import com.example.buensaboruno.domain.entities.ImagenArticulo;
import com.example.buensaboruno.domain.entities.UnidadMedida;
import com.example.buensaboruno.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @GetMapping("/unidad-medida")
    public List<UnidadMedida> getAllUnidadesMedida(){
        return unidadMedidaRepository.findAll();
    }

    @GetMapping("/unidad-medida/{id}")
    public UnidadMedida getUnidadMedidaById(@PathVariable long id){
        return unidadMedidaRepository.findById(id).orElse(null);
    }

    @PostMapping("/unidad-medida")
    public UnidadMedida crearUnidadMedida(@RequestBody UnidadMedida unidadMedida){
        return unidadMedidaRepository.save(unidadMedida);
    }

    @PatchMapping("/unidad-medida/{id}")
    public void eliminarUnidadMedida(@PathVariable Long id) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UnidadMedida no encontrada con id: " + id));
        unidadMedida.setEliminado(true);
        unidadMedidaRepository.save(unidadMedida);
    }

    @PutMapping("/unidad-medida/{id}")
    public UnidadMedida actualizarUnidadMedida(@PathVariable Long id, @RequestBody UnidadMedida datosActualizados) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UnidadMedida no encontrada con id: " + id));
        unidadMedida.setDenominacion(datosActualizados.getDenominacion());
        // Actualiza otros atributos según sea necesario
        return unidadMedidaRepository.save(unidadMedida);
    }
}
