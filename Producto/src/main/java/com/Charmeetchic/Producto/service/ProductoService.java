package com.Charmeetchic.Producto.service;

import com.Charmeetchic.Producto.dto.ProductoMapper;
import com.Charmeetchic.Producto.dto.ProductoRequestDTO;
import com.Charmeetchic.Producto.dto.ProductoResponseDTO;
import com.Charmeetchic.Producto.model.Producto;
import com.Charmeetchic.Producto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        Producto entity = ProductoMapper.toEntity(dto);
        Producto guardado = repository.save(entity);
        return ProductoMapper.toDTO(guardado);
    }

    public List<ProductoResponseDTO> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return ProductoMapper.toDTO(p);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
public ProductoResponseDTO actualizarProducto(Long id, ProductoRequestDTO dto) {
    var producto = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    ProductoMapper.updateEntityFromDto(dto, producto);

    return ProductoMapper.toDTO(repository.save(producto));
}


}
