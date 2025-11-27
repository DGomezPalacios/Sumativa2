package com.Charmeetchic.Producto.config;

import com.Charmeetchic.Producto.model.Producto;
import com.Charmeetchic.Producto.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final ProductoRepository productoRepository;

    public DataLoader(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostConstruct
    public void init() {

        // Si ya hay productos, no cargar nada
        if (productoRepository.count() > 0) {
            return;
        }

        // Producto 1
        Producto p1 = new Producto();
        p1.setNombre("Collar Dorado Minimalista");
        p1.setDescripcion("Collar delicado de acero inoxidable, ideal para uso diario.");
        p1.setPrecio(12990.0);
        p1.setStock(10);
        p1.setMaterial("Acero inoxidable");
        p1.setPeso(12.5);
        p1.setMedidas("45 cm");
        p1.setCategoriaId(1L);

        // Producto 2
        Producto p2 = new Producto();
        p2.setNombre("Aros Perla Vintage");
        p2.setDescripcion("Aros clásicos de perla natural montados en plata 925.");
        p2.setPrecio(15990.0);
        p2.setStock(12);
        p2.setMaterial("Plata 925");
        p2.setPeso(5.0);
        p2.setMedidas("1.8 cm");
        p2.setCategoriaId(1L);

        // Guardar
        productoRepository.save(p1);
        productoRepository.save(p2);

        System.out.println("⭐ Productos iniciales cargados correctamente");
    }
}
