package com.Charmeetchic.Compras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarritoItemDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private ProductoDTO producto;
}
