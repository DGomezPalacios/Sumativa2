package com.Charmeetchic.Contacto.service;

import com.Charmeetchic.Contacto.dto.ContactoRequest;
import com.Charmeetchic.Contacto.dto.ContactoResponse;
import com.Charmeetchic.Contacto.model.Contacto;
import com.Charmeetchic.Contacto.repository.ContactoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoResponse guardar(ContactoRequest req) {

        Contacto c = new Contacto();
        c.setNombre(req.getName());
        c.setEmail(req.getEmail());
        c.setTelefono(req.getPhone());
        c.setMensaje(req.getMessage());
        c.setTipoServicio(req.getServiceType());
        c.setImagenUrl(req.getImageUrl());

        Contacto saved = contactoRepository.save(c);

        return new ContactoResponse(
                saved.getId(),
                "Contacto recibido correctamente",
                "OK"
        );
    }
}
