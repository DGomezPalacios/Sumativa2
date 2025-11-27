package com.Charmeetchic.Contacto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String mensaje;
    private String tipoServicio;
    private String imagenUrl;
}
