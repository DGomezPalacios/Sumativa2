package com.Charmeetchic.Pago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Charmeetchic.Pago.model.Pago;


public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByPedidoId(Long pedidoId);
}