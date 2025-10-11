package com.Charmeetchic.Compras.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data 
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private Long usuarioId; 
    private String estado; //en carrito, pagado, enviado
    private Double total; 
}

