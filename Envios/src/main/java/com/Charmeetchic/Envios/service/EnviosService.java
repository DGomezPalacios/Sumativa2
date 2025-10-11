package com.Charmeetchic.Envios.service;

import com.Charmeetchic.Envios.model.Envios;
import com.Charmeetchic.Envios.repository.EnviosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnviosService {

    private final EnviosRepository enviosRepository;

    public List<Envios> obtenerTodos() {
        return enviosRepository.findAll();
    }

    public Envios guardar(Envios envio) {
        return enviosRepository.save(envio);
    }

    public void eliminar(Long id) {
        enviosRepository.deleteById(id);
    }
}