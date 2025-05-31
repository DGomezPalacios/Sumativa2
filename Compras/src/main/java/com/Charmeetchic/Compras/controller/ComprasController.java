package com.Charmeetchic.Compras.controller;

import com.Charmeetchic.Compras.service.ComprasService;
import com.Charmeetchic.Compras.model.Compras;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@AllArgsConstructor
public class ComprasController {

    private final ComprasService comprasService;

    @GetMapping
    public ResponseEntity<List<Compras>> listarCompras() {
        return ResponseEntity.ok(comprasService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Compras> crearCompra(@RequestBody Compras compras) {
        return ResponseEntity.ok(comprasService.guardar(compras));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compras> actualizarCompra(@PathVariable Long id, @RequestBody Compras compras) {
        compras.setId(id);
        return ResponseEntity.ok(comprasService.guardar(compras));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        comprasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
