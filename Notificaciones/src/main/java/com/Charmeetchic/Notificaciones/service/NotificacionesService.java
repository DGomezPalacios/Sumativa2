package com.Charmeetchic.Notificaciones.service;

import com.Charmeetchic.Notificaciones.model.Notificaciones;
import com.Charmeetchic.Notificaciones.repository.NotificacionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificacionesService {

    private final NotificacionesRepository notificacionesRepository;

    public List<Notificaciones> obtenerTodas() {
        return notificacionesRepository.findAll();
    }

    public Notificaciones guardar(Notificaciones notificacion) {
        return notificacionesRepository.save(notificacion);
    }

    public void eliminar(Long id) {
        notificacionesRepository.deleteById(id);
    }

    public Notificaciones actualizar(Long id, Notificaciones nuevaNotificacion) {
        Notificaciones existente = notificacionesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));

        existente.setDestinatario(nuevaNotificacion.getDestinatario());
        existente.setMensaje(nuevaNotificacion.getMensaje());
        existente.setTipo(nuevaNotificacion.getTipo());
        existente.setEnviado(nuevaNotificacion.isEnviado());

        return notificacionesRepository.save(existente);
    }
}