package com.Charmeetchic.Compras.service;

import com.Charmeetchic.Compras.model.Compras;
import com.Charmeetchic.Compras.repository.ComprasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComprasService {

    private final ComprasRepository comprasRepository;

    public List<Compras> obtenerTodas() {
        return comprasRepository.findAll();
    }

    public Compras guardar(Compras compras) {
        return comprasRepository.save(compras);
    }

    public Compras actualizar(Long id, Compras compraActualizada) {
        Optional<Compras> compraExistente = comprasRepository.findById(id);
        if (compraExistente.isPresent()) {
            Compras compra = compraExistente.get();
            compra.setUsuarioId(compraActualizada.getUsuarioId());
            compra.setEstado(compraActualizada.getEstado());
            compra.setTotal(compraActualizada.getTotal());
            return comprasRepository.save(compra);
        } else {
            throw new RuntimeException("Compra con ID " + id + " no encontrada");
        }
    }

    public void eliminar(Long id) {
        comprasRepository.deleteById(id);
    }
}