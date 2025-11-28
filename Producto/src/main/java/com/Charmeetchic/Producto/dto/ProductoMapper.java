package com.Charmeetchic.Producto.dto;

import com.Charmeetchic.Producto.model.Producto;

public class ProductoMapper {

    // Entity → DTO
    public static ProductoResponseDTO toDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setMaterial(producto.getMaterial());
        dto.setPeso(producto.getPeso());
        dto.setMedidas(producto.getMedidas());
        dto.setCategoriaId(producto.getCategoriaId());
        return dto;
    }

    // DTO → Entity
    public static Producto toEntity(ProductoRequestDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setMaterial(dto.getMaterial());
        p.setPeso(dto.getPeso());
        p.setMedidas(dto.getMedidas());
        p.setCategoriaId(dto.getCategoriaId());
        return p;
    }
    public static void updateEntityFromDto(ProductoRequestDTO dto, Producto entity) {
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
    entity.setPrecio(dto.getPrecio());
    entity.setStock(dto.getStock());
    entity.setMaterial(dto.getMaterial());
    entity.setPeso(dto.getPeso());
    entity.setMedidas(dto.getMedidas());
    entity.setCategoriaId(dto.getCategoriaId());
}

}
