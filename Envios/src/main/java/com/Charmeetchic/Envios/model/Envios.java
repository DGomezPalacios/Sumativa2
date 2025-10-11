package com.Charmeetchic.Envios.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private Long pedidoId; 
    private String direccion; // dirección de entrega
    private String estado; // pendiente, enviado, entregado.
    private String fechaEnvio; 
    private String transportadora; 
}