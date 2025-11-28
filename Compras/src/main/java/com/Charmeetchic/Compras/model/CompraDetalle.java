package com.Charmeetchic.Compras.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CompraDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compras compra;   // <<--- ES UN OBJETO, NO UN ID

    private Long productoId;

    private Integer cantidad;
}
