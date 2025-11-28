package com.Charmeetchic.Producto.controller;

import com.Charmeetchic.Producto.dto.ProductoRequestDTO;
import com.Charmeetchic.Producto.dto.ProductoResponseDTO;
import com.Charmeetchic.Producto.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    public ProductoResponseDTO crear(@Valid @RequestBody ProductoRequestDTO dto) {
        return service.crearProducto(dto);
    }

    @GetMapping
    public List<ProductoResponseDTO> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
    @PutMapping("/{id}")
public ProductoResponseDTO actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ProductoRequestDTO dto
) {
    return service.actualizarProducto(id, dto);
}

}
