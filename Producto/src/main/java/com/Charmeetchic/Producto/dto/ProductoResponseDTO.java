package com.Charmeetchic.Producto.dto;

import lombok.Data;

@Data
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String material;
    private Double peso;
    private String medidas;
    private Long categoriaId;
}
