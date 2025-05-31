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

    public Pago procesarPago(Pago pago) {
        pago.setEstado("APROBADO"); // se podria validar con integracion
        pago.setFechaPago(new Date());
        return pagoRepository.save(pago);
    }

    public List<Pago> buscarPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId);
    }

    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }


    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public Pago actualizarPago(Long id, Pago pagoActualizado) {
        Pago pago = pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        pago.setMonto(pagoActualizado.getMonto());
        pago.setMetodoPago(pagoActualizado.getMetodoPago());
        pago.setEstado(pagoActualizado.getEstado());
        return pagoRepository.save(pago);
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }

}
