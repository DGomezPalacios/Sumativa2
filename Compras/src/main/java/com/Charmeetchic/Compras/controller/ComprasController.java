package com.Charmeetchic.Compras.controller;

import com.Charmeetchic.Compras.dto.CarritoResponse;
import com.Charmeetchic.Compras.model.Compras;
import com.Charmeetchic.Compras.service.ComprasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class ComprasController {

    private final ComprasService comprasService;

    // Obtener carrito completo con productos
    @GetMapping("/carrito-completo/{usuarioId}")
    public CarritoResponse obtenerCarritoCompleto(@PathVariable Long usuarioId) {
        return comprasService.obtenerCarritoCompleto(usuarioId);
    }

    // Agregar producto
    @PostMapping("/carrito/agregar")
    public void agregarProducto(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId
    ) {
        comprasService.agregarProducto(usuarioId, productoId);
    }

    // Actualizar cantidad
    @PutMapping("/carrito/{usuarioId}/producto/{productoId}")
    public void actualizar(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId,
            @RequestParam Integer cantidad
    ) {
        comprasService.actualizarProducto(usuarioId, productoId, cantidad);
    }

    // Eliminar producto
    @DeleteMapping("/carrito/{usuarioId}/producto/{productoId}")
    public void eliminar(@PathVariable Long usuarioId, @PathVariable Long productoId) {
        comprasService.eliminarProducto(usuarioId, productoId);
    }

    // Vaciar carrito
    @DeleteMapping("/carrito/{usuarioId}")
    public void vaciar(@PathVariable Long usuarioId) {
        comprasService.vaciarCarrito(usuarioId);
    }

    // Confirmar compra
    @PostMapping("/carrito/{usuarioId}/confirmar")
    public void confirmar(@PathVariable Long usuarioId) {
        comprasService.confirmarCompra(usuarioId);
    }

    // CRUD base
    @GetMapping
    public List<Compras> obtenerTodas() {
        return comprasService.obtenerTodas();
    }
}
