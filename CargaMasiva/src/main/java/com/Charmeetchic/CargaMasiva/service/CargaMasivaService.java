package com.Charmeetchic.CargaMasiva.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Charmeetchic.CargaMasiva.model.CargaMasiva;
import com.Charmeetchic.CargaMasiva.repository.CargaMasivaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CargaMasivaService {
    private ProductoRepository productoRepository;
    private CargaMasivaRepository cargaMasivaRepository;

    //procesar archivo CSV y guardar productos en la base de datos
    public int cargarProductosMasivamente(MultipartFile file) throws Exception {
        List<Producto> productos = new ArrayList<>();

        // leer archivo CSV linea x linea
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                //saltar encabezado con los nimbres
                if (primera) {
                    primera = false;
                    continue;
                }
                //separar los campos por coma
                String[] campos = linea.split(",");
                //crear objeto Producto y asignar los campos
                Producto producto = new Producto();
                producto.setNombre(campos[0]);
                producto.setDescripcion(campos[1]);
                producto.setPrecio(Double.valueOf(campos[2]));
                producto.setMaterial(campos[3]);
                producto.setPeso(Double.valueOf(campos[4]));
                producto.setMedidas(campos[5]);
                producto.setCategoriaId(Long.valueOf(campos[6]));
                productos.add(producto);
            }
        }
        //guardar todos los productos de una vez
        productoRepository.saveAll(productos);

        // registrar el evento de carga masiva
        CargaMasiva evento = new CargaMasiva();
        evento.setFechaCarga(new Date());
        evento.setCantidadProductos(productos.size());
        cargaMasivaRepository.save(evento);

        //retornar la cantidad de productos cargados
        return productos.size();
    }
}
