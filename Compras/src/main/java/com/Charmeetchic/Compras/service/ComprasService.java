package com.Charmeetchic.Compras.service;

import com.Charmeetchic.Compras.model.Compras;
import com.Charmeetchic.Compras.model.Compras.DetalleCompra;
import com.Charmeetchic.Compras.repository.ComprasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComprasService {

    private final ComprasRepository comprasRepository;

    public List<Compras> obtenerTodas() {
        return comprasRepository.findAll();
    }

    public Compras guardar(Compras compras) {
        double total = 0;
        for (DetalleCompra detalle : compras.getDetalles()) {
            detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());
            total += detalle.getSubtotal();
        }
        compras.setTotal(total);
        return comprasRepository.save(compras);
    }

    public void eliminar(Long id) {
        comprasRepository.deleteById(id);
    }
}