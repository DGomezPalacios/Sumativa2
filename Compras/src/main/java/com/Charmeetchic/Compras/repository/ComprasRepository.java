package com.Charmeetchic.Compras.repository;

import com.Charmeetchic.Compras.model.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Long> {

    Optional<Compras> findByUsuarioIdAndEstado(Long usuarioId, String estado);
}
