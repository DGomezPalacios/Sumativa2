package com.Charmeetchic.Inventario.controller;

import com.Charmeetchic.Inventario.assembler.InventarioModelAssembler;
import com.Charmeetchic.Inventario.model.Inventario;
import com.Charmeetchic.Inventario.service.InventarioService;
import lombok.AllArgsConstructor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Inventario v2", description = "Operaciones de Inventario HATEOAS")
@RestController
@RequestMapping("/api/v2/inventario")
@AllArgsConstructor
public class InventarioControllerV2 {

    private final InventarioService inventarioService;
    private final InventarioModelAssembler assembler;

    @Operation(summary = "Listar inventarios", description = "Lista todos los inventarios.")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Inventario>>> listarInventario() {
        List<EntityModel<Inventario>> lista = inventarioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(lista,
                linkTo(methodOn(InventarioControllerV2.class).listarInventario()).withSelfRel()));
    }

    @Operation(summary = "Obtener inventario", description = "Obtiene inventario por ID.")
    @ApiResponse(responseCode = "200", description = "Inventario encontrado")
    @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Inventario>> obtenerPorId(
            @Parameter(description = "ID del inventario") @PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.obtenerPorId(id);
        return inventario.map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear inventario", description = "Crea un nuevo inventario.")
    @ApiResponse(responseCode = "201", description = "Inventario creado")
    @PostMapping
    public ResponseEntity<EntityModel<Inventario>> crearInventario(@RequestBody Inventario inventario) {
        Inventario creado = inventarioService.guardar(inventario);
        return ResponseEntity.created(linkTo(methodOn(InventarioControllerV2.class)
                        .obtenerPorId(creado.getId())).toUri())
                .body(assembler.toModel(creado));
    }

    @Operation(summary = "Actualizar inventario", description = "Actualiza inventario existente.")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Inventario>> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioService.actualizar(id, inventario);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Eliminar inventario", description = "Elimina un inventario por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
