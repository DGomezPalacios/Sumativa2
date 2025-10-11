package com.Charmeetchic.Usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity //indica que la clase es una entidad
@Data //trae getters and setters
public class Usuario {
    @Id //este artributo sera la PK
    @GeneratedValue(strategy =GenerationType.IDENTITY) /*autoincremental*/

    private Long id;
    private String nombre;
    private String apellido;

    @Column (unique =true) // estos son unicos en la bbdd
    private String correo;
    private String contrasenia;

    @Enumerated (EnumType.STRING)//guarda el enum como texto
    private Rol rol;
    public enum Rol {
        COMPRADOR,
        VENDEDOR,
        ADMIN
    }

    

}
