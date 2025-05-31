package com.Charmeetchic.Categoria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Charmeetchic.Categoria.model.Categoria;
import com.Charmeetchic.Categoria.service.CategoriaService;

import lombok.AllArgsConstructor;

@RestController 
@RequestMapping("/api/categorias") 
@AllArgsConstructor 
public class CategoriaController {

    private final CategoriaService categoriaService;

    //crear una nueva categoria
    @PostMapping
    public ResponseEntity<Categoria> agregarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.agregarCategoria(categoria));
    }

    //listar todas las categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}
