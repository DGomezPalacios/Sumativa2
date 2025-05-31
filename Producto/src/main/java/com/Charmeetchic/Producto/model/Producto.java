package com.Charmeetchic.Producto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String material;       
    private Double peso;           
    private String medidas;        
    private Long categoriaId; //relacion por id con la categoria


}
