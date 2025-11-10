package com.Charmeetchic.CargaMasiva.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Charmeetchic.CargaMasiva.model.CargaMasiva;
import com.Charmeetchic.CargaMasiva.repository.CargaMasivaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CargaMasivaService {
    private final CargaMasivaRepository cargaMasivaRepository;

 //lee el arcvhivo
    public int cargarProductosMasivamente(MultipartFile file) throws Exception {
        int cantidad = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            boolean primera = true;
            String linea;
            while ((linea = br.readLine()) != null) {
                if (primera) { 
                    primera = false;
                    continue;
                }
                if (!linea.trim().isEmpty()) {
                    cantidad++;
                }
            }
        }
        CargaMasiva evento = new CargaMasiva();
        evento.setFechaCarga(new Date());
        evento.setCantidadProductos(cantidad);
        cargaMasivaRepository.save(evento);
        return cantidad;
    }

    // solo para fin de pruebas es la creacion manual
    public CargaMasiva crearEvento(CargaMasiva evento) {
        return cargaMasivaRepository.save(evento);
    }

    public List<CargaMasiva> listarEventos() {
        return cargaMasivaRepository.findAll();
    }

    public Optional<CargaMasiva> buscarEventoPorId(Long id) {
        return cargaMasivaRepository.findById(id);
    }

    public CargaMasiva actualizarEvento(Long id, CargaMasiva actualizado) {
        CargaMasiva evento = cargaMasivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        evento.setFechaCarga(actualizado.getFechaCarga());
        evento.setCantidadProductos(actualizado.getCantidadProductos());
        return cargaMasivaRepository.save(evento);
    }

    public void eliminarEvento(Long id) {
        cargaMasivaRepository.deleteById(id);
    }
}