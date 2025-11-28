package com.Charmeetchic.Compras.client;

import com.Charmeetchic.Compras.dto.ProductoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductosClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "http://localhost:8088/productos";

    public ProductoDTO obtenerProducto(Long id) {
        String url = BASE_URL + "/" + id;
        return restTemplate.getForObject(url, ProductoDTO.class);
    }
}
