package com.example.buensaboruno.controllers;

import com.example.buensaboruno.domain.entities.Categoria;
import com.example.buensaboruno.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categoria")
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/categoria/{id}")
    public Categoria getCategoriaById(@PathVariable Long id){
        return categoriaRepository.findById(id).orElse(null);
    }

    @PostMapping("/categoria")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        try {
            categoria.getSubCategorias().forEach(subcategoria -> subcategoria.setCategoriaPadre(categoria));
            return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al crear Categoria");
        }
    }

    @PatchMapping("/categoria/{id}")
    public void eliminarCategoria(@PathVariable Long id) {
        Categoria categoria = getCategoriaById(id);
        categoria.setEliminado(true);
        categoriaRepository.save(categoria);
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            categoria.getSubCategorias().forEach(subcategoria -> subcategoria.setCategoriaPadre(categoria));
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
            if (!optionalCategoria.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. No se encontró la Categoria");
            }
            Categoria searchedEntity = optionalCategoria.get();
            categoria.setCategoriaPadre(searchedEntity.getCategoriaPadre());
            return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno. Por favor intente luego");
        }
    }

}
