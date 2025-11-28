package com.Charmeetchic.Compras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarritoResponse {
    private Long id;
    private Long usuarioId;
    private String estado;
    private double total;
    private List<CarritoItemDTO> items;
}
