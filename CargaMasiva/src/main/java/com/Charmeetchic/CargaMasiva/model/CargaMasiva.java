package com.Charmeetchic.CargaMasiva.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CargaMasiva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String archivoCSV;
    private Date fechaCarga;

    // Métodos de importación
    public void importarProductos() {
        System.out.println("Importando productos desde archivo");
        // Aquí iría la lógica de lectura del CSV y carga a la base de datos
    }

    public void importarCategorias() {
        System.out.println("Importando categorías desde archivo");
        // Aquí iría la lógica correspondiente
    }

    public void importarStock() {
        System.out.println("Importando stock desde archivo");   
        // Aquí iría la lógica correspondiente
    }
}
