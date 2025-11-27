package com.Charmeetchic.Producto.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoRequestDTO {

    @NotBlank
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @NotNull
    @Min(0)
    private Double precio;

    @NotNull
    @Min(0)
    private Integer stock;

    private String material;

    private Double peso;

    private String medidas;

    @NotNull
    private Long categoriaId;
}
