package com.Charmeetchic.CargaMasiva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Charmeetchic.CargaMasiva.model.CargaMasiva;
import com.Charmeetchic.CargaMasiva.service.CargaMasivaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/carga-masiva")
@AllArgsConstructor
public class CargaMasivaController {

    private final CargaMasivaService cargaMasivaService;

    // para registrar cuando se cargue un archivo
    @PostMapping("/productos")
    public ResponseEntity<String> cargarProductos(@RequestParam("file") MultipartFile file) {
        try {
            int cantidad = cargaMasivaService.cargarProductosMasivamente(file);
            return ResponseEntity.ok("Se cargaron " + cantidad + " productos correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CargaMasiva> crearEvento(@RequestBody CargaMasiva evento) {
        return ResponseEntity.ok(cargaMasivaService.crearEvento(evento));
    }

    @GetMapping
    public ResponseEntity<List<CargaMasiva>> listarEventos() {
        return ResponseEntity.ok(cargaMasivaService.listarEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargaMasiva> buscarEventoPorId(@PathVariable Long id) {
        Optional<CargaMasiva> evento = cargaMasivaService.buscarEventoPorId(id);
        return evento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargaMasiva> actualizarEvento(@PathVariable Long id, @RequestBody CargaMasiva actualizado) {
        return ResponseEntity.ok(cargaMasivaService.actualizarEvento(id, actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        cargaMasivaService.eliminarEvento(id);
        return ResponseEntity.noContent().build();
    }
}