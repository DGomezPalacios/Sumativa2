package com.Charmeetchic.Compras.controller;

import com.Charmeetchic.Compras.model.Compras;
import com.Charmeetchic.Compras.model.CompraDetalle;
import com.Charmeetchic.Compras.service.ComprasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@AllArgsConstructor
public class ComprasController {

    private final ComprasService comprasService;

    // Endpoints
    // Obtener carrito completo del usuario
    @GetMapping("/carrito/{usuarioId}")
    public List<CompraDetalle> obtenerCarrito(@PathVariable Long usuarioId) {
        Compras carrito = comprasService.obtenerOCrearCarrito(usuarioId);
        return comprasService.obtenerDetalles(carrito.getId());
    }

    // Agregar producto al carrito
    @PostMapping("/carrito/agregar")
    public void agregarProducto(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId
    ) {
        comprasService.agregarProducto(usuarioId, productoId);
    }

    // Eliminar producto del carrito
    @DeleteMapping("/carrito/{usuarioId}/producto/{productoId}")
    public void eliminarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId
    ) {
        comprasService.eliminarProducto(usuarioId, productoId);
    }

    // Vaciar carrito
    @DeleteMapping("/carrito/{usuarioId}")
    public void vaciarCarrito(@PathVariable Long usuarioId) {
        comprasService.vaciarCarrito(usuarioId);
    }

    // Confirmar compra
    @PostMapping("/carrito/{usuarioId}/confirmar")
    public void confirmarCompra(@PathVariable Long usuarioId) {
        comprasService.confirmarCompra(usuarioId);
    }


    // CRUDgit
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
