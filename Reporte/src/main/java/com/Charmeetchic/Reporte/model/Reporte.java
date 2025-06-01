package com.Charmeetchic.Reporte.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String tipo; // ventas, inventario, clientes, etc.
    private String contenido;  
    private String generadoPor; // usuario o sistema que generó el reporte
    private String fecha; 
}