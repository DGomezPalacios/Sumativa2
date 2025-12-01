package com.Charmeetchic.Usuario.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String contrasenia;
}
