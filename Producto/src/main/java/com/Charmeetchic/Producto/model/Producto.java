package com.Charmeetchic.Producto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

@Column(nullable = false)
private Integer precio;

    @Column(nullable = false)
    private Integer stock; 

    private String material;

    private Double peso;

    private String medidas;

    @Column(name = "categoria_id")
    private Long categoriaId; // relación con categoría por ID
}

