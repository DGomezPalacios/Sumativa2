package com.Charmeetchic.Compras.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data 
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private Long usuarioId; 
    private String estado; //en carrito, pagado, enviado
    private Double total; 

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "compra_id")
    private List<DetalleCompra> detalles; 

    // Subclase detalle de compras
    @Entity
    @Data
    public static class DetalleCompra {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long productoId; 
        private int cantidad; 
        private Double precioUnitario;
        private Double subtotal; // precioUnitario * cantidad
    }
}
