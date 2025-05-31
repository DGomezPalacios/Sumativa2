package com.Charmeetchic.Producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Charmeetchic.Producto.model.Producto;
import com.Charmeetchic.Producto.repository.ProductoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ProductoService {
    private final ProductoRepository productoRepository;

    //crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    //editar un producto 
    public Producto modificarProducto(Long id, Producto productoActualizado) {
           Producto producto = productoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
           producto.setNombre(productoActualizado.getNombre());
           producto.setDescripcion(productoActualizado.getDescripcion());
           producto.setPrecio(productoActualizado.getPrecio());
           producto.setMaterial(productoActualizado.getMaterial());
           producto.setPeso(productoActualizado.getPeso());
           producto.setMedidas(productoActualizado.getMedidas());
           producto.setCategoriaId(productoActualizado.getCategoriaId());
           return productoRepository.save(producto);
        }


    //eliminar un producto por su id
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    //buscar productos por nombre
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    //buscar un producto por ID
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    //listar productos
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }


}