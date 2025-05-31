package com.Charmeetchic.Inventario.service;

import com.Charmeetchic.Inventario.model.Inventario;
import com.Charmeetchic.Inventario.repository.InventarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    public Inventario guardar(Inventario inventario) {
        inventario.verificarAlerta();
        return inventarioRepository.save(inventario);
    }

    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }

    public Optional<Inventario> buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    public Inventario actualizar(Long id, Inventario inventario) {
        Optional<Inventario> inventarioExistente = inventarioRepository.findById(id);
        if (inventarioExistente.isPresent()) {
            inventario.setId(id); 
            inventario.verificarAlerta();
            return inventarioRepository.save(inventario);
        } else {
            throw new RuntimeException("Inventario con ID " + id + " no encontrado");
        }
    }
}