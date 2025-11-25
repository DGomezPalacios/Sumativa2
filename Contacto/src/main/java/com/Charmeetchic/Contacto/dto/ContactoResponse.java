package com.Charmeetchic.Contacto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactoResponse {
    private String status;
    private String message;
}
