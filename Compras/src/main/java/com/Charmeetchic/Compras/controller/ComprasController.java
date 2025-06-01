package com.Charmeetchic.Compras.controller;

import com.Charmeetchic.Compras.model.Compras;
import com.Charmeetchic.Compras.service.ComprasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@AllArgsConstructor
public class ComprasController {

    private final ComprasService comprasService;

    @GetMapping
    public List<Compras> obtenerTodas() {
        return comprasService.obtenerTodas();
    }

    @PostMapping
    public Compras guardar(@RequestBody Compras compra) {
        return comprasService.guardar(compra);
    }

    @PutMapping("/{id}")
    public Compras actualizar(@PathVariable Long id, @RequestBody Compras compraActualizada) {
        return comprasService.actualizar(id, compraActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        comprasService.eliminar(id);
    }
}