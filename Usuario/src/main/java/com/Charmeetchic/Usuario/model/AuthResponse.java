package com.Charmeetchic.Usuario.model;

import lombok.Data;

@Data
public class AuthResponse {

    private String token;
    private Usuario usuario;

    public AuthResponse() {}

    public AuthResponse(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }
}
