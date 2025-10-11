package com.Charmeetchic.Pago.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long pedidoId;       

    private Double monto;        

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;      
    private String metodoPago;   

    private String estado;  

}

