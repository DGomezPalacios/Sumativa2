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

    /**
     * Procesa un archivo CSV y registra un evento de carga.
     */
    public CargaMasiva cargarArchivo(MultipartFile file) throws Exception {
        String nombreArchivo = file.getOriginalFilename();

        // Validación simple
        if (nombreArchivo == null || !nombreArchivo.endsWith(".csv")) {
            throw new IllegalArgumentException("Debe subir un archivo CSV válido.");
        }

        // Lectura rápida para verificar líneas (opcional)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            int lineas = 0;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) lineas++;
            }
            System.out.println("Archivo leído correctamente con " + lineas + " líneas.");
        }

        // Registrar evento
        CargaMasiva evento = new CargaMasiva();
        evento.setArchivoCSV(nombreArchivo);
        evento.setFechaCarga(new Date());

        return cargaMasivaRepository.save(evento);
    }

    // Métodos CRUD y de gestión
    public List<CargaMasiva> listarEventos() {
        return cargaMasivaRepository.findAll();
    }

    public Optional<CargaMasiva> buscarEventoPorId(Long id) {
        return cargaMasivaRepository.findById(id);
    }

    public CargaMasiva actualizarEvento(Long id, CargaMasiva actualizado) {
        CargaMasiva evento = cargaMasivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        evento.setArchivoCSV(actualizado.getArchivoCSV());
        evento.setFechaCarga(actualizado.getFechaCarga());

        return cargaMasivaRepository.save(evento);
    }

    public void eliminarEvento(Long id) {
        cargaMasivaRepository.deleteById(id);
    }

    // --- Llamadas a los métodos de importación ---
    public void importarProductos(Long id) {
        CargaMasiva evento = cargaMasivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        evento.importarProductos();
    }

    public void importarCategorias(Long id) {
        CargaMasiva evento = cargaMasivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        evento.importarCategorias();
    }

    public void importarStock(Long id) {
        CargaMasiva evento = cargaMasivaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        evento.importarStock();
    }
}
