package com.Charmeetchic.Inventario.repository;

import java.util.Optional;

import com.Charmeetchic.Inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProductoId(Long productoId);
}
