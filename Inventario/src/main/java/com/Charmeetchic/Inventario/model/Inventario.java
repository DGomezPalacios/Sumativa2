package com.Charmeetchic.Inventario.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private Long productoId;
    private int stock; 
    private int stockMinimo; // stock minimo para generar alerta
    private boolean alerta; // alerta en caso de bajar stock
    
    public void verificarAlerta() {
        this.alerta = stock <= stockMinimo;
    }
}