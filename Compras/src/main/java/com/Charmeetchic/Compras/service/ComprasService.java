package com.Charmeetchic.Compras.service;

import com.Charmeetchic.Compras.client.ProductosClient;
import com.Charmeetchic.Compras.dto.CarritoItemDTO;
import com.Charmeetchic.Compras.dto.CarritoResponse;
import com.Charmeetchic.Compras.dto.ProductoDTO;
import com.Charmeetchic.Compras.model.CompraDetalle;
import com.Charmeetchic.Compras.model.Compras;
import com.Charmeetchic.Compras.repository.CompraDetalleRepository;
import com.Charmeetchic.Compras.repository.ComprasRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComprasService {

    private final ComprasRepository comprasRepository;
    private final CompraDetalleRepository detalleRepository;
    private final ProductosClient productosClient;

    // ----------------------------------------
    // Obtener todas las compras (para el controlador)
    // ----------------------------------------
    public List<Compras> obtenerTodas() {
        return comprasRepository.findAll();
    }

    // ----------------------------------------
    // Crear carrito si no existe
    // ----------------------------------------
    public Compras obtenerOCrearCarrito(Long usuarioId) {
        Optional<Compras> existente = comprasRepository.findAll()
                .stream()
                .filter(c -> c.getUsuarioId().equals(usuarioId) && c.getEstado().equals("en_carrito"))
                .findFirst();

        if (existente.isPresent()) {
            return existente.get();
        }

        Compras nuevo = new Compras();
        nuevo.setUsuarioId(usuarioId);
        nuevo.setEstado("en_carrito");
        nuevo.setTotal(0.0);

        return comprasRepository.save(nuevo);
    }

    // ----------------------------------------
    // Obtener detalles del carrito
    // ----------------------------------------
    public List<CompraDetalle> obtenerDetalles(Long compraId) {
        return detalleRepository.findByCompraId(compraId);
    }

    // ----------------------------------------
    // Carrito completo con productos
    // ----------------------------------------
    public CarritoResponse obtenerCarritoCompleto(Long usuarioId) {

        Compras carrito = obtenerOCrearCarrito(usuarioId);

        List<CompraDetalle> detalles = detalleRepository.findByCompraId(carrito.getId());

        return new CarritoResponse(
                carrito.getId(),
                usuarioId,
                carrito.getEstado(),
                carrito.getTotal(),
                detalles.stream().map(det -> {
                    ProductoDTO p = productosClient.obtenerProducto(det.getProductoId());
                    return new CarritoItemDTO(
                            det.getId(),
                            det.getProductoId(),
                            det.getCantidad(),
                            p
                    );
                }).toList()
        );
    }

    // ----------------------------------------
    // Agregar producto
    // ----------------------------------------
    public void agregarProducto(Long usuarioId, Long productoId) {

        Compras carrito = obtenerOCrearCarrito(usuarioId);

        Optional<CompraDetalle> existenteOpt =
                detalleRepository.findByCompraIdAndProductoId(carrito.getId(), productoId);

        if (existenteOpt.isPresent()) {
            CompraDetalle existente = existenteOpt.get();
            existente.setCantidad(existente.getCantidad() + 1);
            detalleRepository.save(existente);
        } else {
            CompraDetalle nuevo = new CompraDetalle();
            nuevo.setCompra(carrito);  // <<--- CORRECTO
            nuevo.setProductoId(productoId);
            nuevo.setCantidad(1);
            detalleRepository.save(nuevo);
        }

        recalcularTotal(carrito.getId());
    }

    // ----------------------------------------
    // Actualizar cantidad
    // ----------------------------------------
    public void actualizarProducto(Long usuarioId, Long productoId, Integer cantidad) {

        Compras carrito = obtenerOCrearCarrito(usuarioId);

        Optional<CompraDetalle> existenteOpt =
                detalleRepository.findByCompraIdAndProductoId(carrito.getId(), productoId);

        if (existenteOpt.isPresent()) {
            CompraDetalle detalle = existenteOpt.get();
            detalle.setCantidad(cantidad);
            detalleRepository.save(detalle);
        }

        recalcularTotal(carrito.getId());
    }

    // ----------------------------------------
    // Eliminar producto
    // ----------------------------------------
    public void eliminarProducto(Long usuarioId, Long productoId) {
        Compras carrito = obtenerOCrearCarrito(usuarioId);
        detalleRepository.deleteByCompraIdAndProductoId(carrito.getId(), productoId);
        recalcularTotal(carrito.getId());
    }

    // ----------------------------------------
    // Vaciar carrito
    // ----------------------------------------
    public void vaciarCarrito(Long usuarioId) {
        Compras carrito = obtenerOCrearCarrito(usuarioId);
        detalleRepository.deleteByCompraId(carrito.getId());
        carrito.setTotal(0.0);
        comprasRepository.save(carrito);
    }

    // ----------------------------------------
    // Confirmar compra
    // ----------------------------------------
    public void confirmarCompra(Long usuarioId) {
        Compras carrito = obtenerOCrearCarrito(usuarioId);
        carrito.setEstado("pagado");
        comprasRepository.save(carrito);
    }

    // ----------------------------------------
    // Recalcular total
    // ----------------------------------------
    private void recalcularTotal(Long compraId) {

        List<CompraDetalle> detalles = detalleRepository.findByCompraId(compraId);

        double total = detalles.stream().mapToDouble(det -> {
            ProductoDTO producto = productosClient.obtenerProducto(det.getProductoId());
            return producto.getPrecio() * det.getCantidad();
        }).sum();

        Compras compra = comprasRepository.findById(compraId).orElseThrow();
        compra.setTotal(total);
        comprasRepository.save(compra);
    }
}
