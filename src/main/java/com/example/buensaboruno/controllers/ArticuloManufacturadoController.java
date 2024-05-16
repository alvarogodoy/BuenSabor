package com.example.buensaboruno.controllers;

import com.example.buensaboruno.domain.entities.ArticuloManufacturado;
import com.example.buensaboruno.repositories.ArticuloManufacturadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticuloManufacturadoController{

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @GetMapping("/articulos-manufacturados")
    public List<ArticuloManufacturado> getAllArticulosManufacturados(){
        return articuloManufacturadoRepository.findAll();
    }
}
