package com.Charmeetchic.Reporte.service;

import com.Charmeetchic.Reporte.model.Reporte;
import com.Charmeetchic.Reporte.repository.ReporteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReporteService {

    private final ReporteRepository reporteRepository;

    // Obtener todos los reportes
    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    // Guardar o actualizar un reporte
    public Reporte guardar(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    // Eliminar un reporte por ID
    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }
}

