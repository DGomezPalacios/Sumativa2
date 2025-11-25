package com.Charmeetchic.Contacto.dto;

import lombok.Data;

@Data
public class ContactoRequest {

    private String name;
    private String email;
    private String phone;
    private String message;
    private String serviceType;
    private String imageUrl;
}
