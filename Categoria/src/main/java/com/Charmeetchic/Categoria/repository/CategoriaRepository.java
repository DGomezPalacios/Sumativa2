package com.Charmeetchic.Categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Charmeetchic.Categoria.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
