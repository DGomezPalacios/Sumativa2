package com.Charmeetchic.Contacto.controller;

import com.Charmeetchic.Contacto.dto.ContactoRequest;
import com.Charmeetchic.Contacto.dto.ContactoResponse;
import com.Charmeetchic.Contacto.service.ContactoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@AllArgsConstructor
public class ContactoController {

    private final ContactoService contactoService;

    @PostMapping
    public ContactoResponse guardar(@RequestBody ContactoRequest req) {
        return contactoService.guardar(req);
    }
}
