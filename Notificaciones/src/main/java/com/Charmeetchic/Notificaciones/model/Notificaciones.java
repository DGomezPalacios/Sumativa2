package com.Charmeetchic.Notificaciones.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String destinatario; // correo o nombre del destinatario
    private String mensaje; 
    private String tipo; // email, push, sms
    private boolean enviado; 
}