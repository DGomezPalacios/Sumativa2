package com.Charmeetchic.Notificaciones.controller;

import com.Charmeetchic.Notificaciones.model.Notificaciones;
import com.Charmeetchic.Notificaciones.service.NotificacionesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@AllArgsConstructor
public class NotificacionesController {

    private final NotificacionesService notificacionesService;

    @GetMapping
    public ResponseEntity<List<Notificaciones>> listarNotificaciones() {
        return ResponseEntity.ok(notificacionesService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Notificaciones> crearNotificacion(@RequestBody Notificaciones notificacion) {
        return ResponseEntity.ok(notificacionesService.guardar(notificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificaciones> actualizarNotificacion(@PathVariable Long id, @RequestBody Notificaciones notificacion) {
        notificacion.setId(id);
        return ResponseEntity.ok(notificacionesService.guardar(notificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificacionesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
