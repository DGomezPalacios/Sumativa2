package com.Charmeetchic.Usuario.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)
    private String correo;

    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        COMPRADOR,
        VENDEDOR,
        ADMIN
    }
}
