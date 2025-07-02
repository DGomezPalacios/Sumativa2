package com.Charmeetchic.Inventario.service;

import com.Charmeetchic.Inventario.model.Inventario;
import com.Charmeetchic.Inventario.repository.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarConAlertaActivada() {
        Inventario inventario = new Inventario();
        inventario.setStock(3);
        inventario.setStockMinimo(5);

        when(inventarioRepository.save(any())).thenReturn(inventario);

        Inventario resultado = inventarioService.guardar(inventario);

        assertTrue(resultado.isAlerta());
        verify(inventarioRepository).save(inventario);
    }
}
