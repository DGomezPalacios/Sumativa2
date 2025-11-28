package com.Charmeetchic.Compras.repository;

import com.Charmeetchic.Compras.model.CompraDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, Long> {

    List<CompraDetalle> findByCompraId(Long compraId);

    Optional<CompraDetalle> findByCompraIdAndProductoId(Long compraId, Long productoId);

    void deleteByCompraId(Long compraId);

    void deleteByCompraIdAndProductoId(Long compraId, Long productoId);
}
