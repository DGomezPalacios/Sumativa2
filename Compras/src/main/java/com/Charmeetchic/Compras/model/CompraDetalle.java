package com.Charmeetchic.Compras.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CompraDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long compraId;
    private Long productoId;
    private Integer cantidad;
}
