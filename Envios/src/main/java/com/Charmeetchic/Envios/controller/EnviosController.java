package com.Charmeetchic.Envios.controller;

import com.Charmeetchic.Envios.model.Envios;
import com.Charmeetchic.Envios.service.EnviosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@AllArgsConstructor
public class EnviosController {

    private final EnviosService enviosService;

    @GetMapping
    public ResponseEntity<List<Envios>> listarEnvios() {
        return ResponseEntity.ok(enviosService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Envios> crearEnvio(@RequestBody Envios envio) {
        return ResponseEntity.ok(enviosService.guardar(envio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envios> actualizarEnvio(@PathVariable Long id, @RequestBody Envios envio) {
        envio.setId(id);
        return ResponseEntity.ok(enviosService.guardar(envio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Long id) {
        enviosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
