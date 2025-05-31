package com.Charmeetchic.Categoria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Charmeetchic.Categoria.model.Categoria;
import com.Charmeetchic.Categoria.repository.CategoriaRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

//agregar categoria
    public Categoria agregarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
//listar categroais
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
}
