package com.Charmeetchic.Compras.repository;

import com.Charmeetchic.Compras.model.CompraDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, Long> {

    List<CompraDetalle> findByCompraId(Long compraId);

    void deleteByCompraId(Long compraId);

    void deleteByCompraIdAndProductoId(Long compraId, Long productoId);
}
