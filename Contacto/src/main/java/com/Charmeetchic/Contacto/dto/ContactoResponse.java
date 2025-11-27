package com.Charmeetchic.Contacto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactoResponse {
    private Long id;
    private String message;
    private String status;
}
