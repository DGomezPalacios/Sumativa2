package com.Charmeetchic.Reporte.service;

import com.Charmeetchic.Reporte.model.Reporte;
import com.Charmeetchic.Reporte.repository.ReporteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReporteService {

    private final ReporteRepository reporteRepository;

    // Obtener todos los reportes
    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    // Guardar un nuevo reporte
    public Reporte guardar(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    // Actualizar un reporte existente
    public Reporte actualizar(Long id, Reporte reporteActualizado) {
        Optional<Reporte> reporteExistente = reporteRepository.findById(id);
        if (reporteExistente.isPresent()) {
            reporteActualizado.setId(id);
            return reporteRepository.save(reporteActualizado);
        } else {
            throw new RuntimeException("Reporte no encontrado con ID: " + id);
        }
    }

    // Eliminar un reporte por ID
    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }
}