package com.Charmeetchic.Inventario.controller;

import com.Charmeetchic.Inventario.model.Inventario;
import com.Charmeetchic.Inventario.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Inventario", description = "Operaciones del inventario OAS")
@RestController
@RequestMapping("/api/inventario")
@AllArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @Operation(summary = "Listar inventarios", description = "Lista todos los inventarios.")
    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        return ResponseEntity.ok(inventarioService.obtenerTodos());
    }

    @Operation(summary = "Crear inventario", description = "Crea un nuevo inventario.")
    @ApiResponse(responseCode = "200", description = "Inventario creado correctamente")
    @PostMapping
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.guardar(inventario));
    }

    @Operation(summary = "Actualizar inventario", description = "Actualiza inventario existente.")
    @ApiResponse(responseCode = "200", description = "Inventario actualizado correctamente")
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(
            @Parameter(description = "ID del inventario a actualizar") @PathVariable Long id,
            @RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.actualizar(id, inventario));
    }

    @Operation(summary = "Eliminar inventario", description = "Elimina un inventario por su ID.")
    @ApiResponse(responseCode = "204", description = "Inventario eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(
            @Parameter(description = "ID del inventario a eliminar") @PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
