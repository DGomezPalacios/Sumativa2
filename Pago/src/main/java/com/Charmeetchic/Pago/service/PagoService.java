package com.Charmeetchic.Pago.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Charmeetchic.Pago.model.Pago;
import com.Charmeetchic.Pago.repository.PagoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PagoService {
     private PagoRepository pagoRepository;

    //nuevo pago (Aprobado)
    public Pago procesarPago(Pago pago) {
        pago.setEstado("APROBADO"); // se podria validar con integracion
        pago.setFechaPago(new Date());
        return pagoRepository.save(pago);
    }

    //buscar pagos por pedido
    public List<Pago> buscarPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId);
    }

    //buscar pago por ID
    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }

    //listar todos los pagos
    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }


}
