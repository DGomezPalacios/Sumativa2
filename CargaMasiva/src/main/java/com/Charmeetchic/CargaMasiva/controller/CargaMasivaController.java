package com.Charmeetchic.CargaMasiva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Charmeetchic.CargaMasiva.service.CargaMasivaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/carga-masiva")
@AllArgsConstructor
public class CargaMasivaController {

    private final CargaMasivaService cargaMasivaService;

    // cargar arichovs masivamente
    @PostMapping("/productos")
    public ResponseEntity<String> cargarProductos(@RequestParam("file") MultipartFile file) {
        try {
            int cantidad = cargaMasivaService.cargarProductosMasivamente(file);
            return ResponseEntity.ok("Se cargaron " + cantidad + " productos correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar el archivo: " + e.getMessage());
        }
    }
}