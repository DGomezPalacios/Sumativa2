package com.Charmeetchic.Inventario;

import com.Charmeetchic.Inventario.model.Inventario;
import com.Charmeetchic.Inventario.service.InventarioService;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

import java.util.Locale;
import java.util.Random;

@Profile("dev")
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final InventarioService inventarioService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es", "CL"));
        Random random = new Random();

        // inventarioService.obtenerTodos().forEach(inventario -> inventarioService.eliminar(inventario.getId()));
        // System.out.println("Base de datos de inventario limpia para repoblación.");

        int numberOfItemsToGenerate = 20;
        System.out.println("Generando " + numberOfItemsToGenerate + " elementos de inventario con DataFaker...");

        for (int i = 1; i <= numberOfItemsToGenerate; i++) {
            Inventario inventario = new Inventario();
            inventario.setProductoId((long) i); 

            int stock = faker.number().numberBetween(10, 500);
            inventario.setStock(stock);

            int stockMinimo = faker.number().numberBetween(5, (int) (stock * 0.3));
            inventario.setStockMinimo(stockMinimo);
            
            // inventario.verificarAlerta(); // Esto es opcional si usas @PrePersist/@PreUpdate

            inventarioService.guardar(inventario);
            System.out.println("Creado: " + inventario);
        }
        System.out.println("Proceso de poblamiento de inventario completado.");
    }
}