package com.Charmeetchic.Usuario.service;

import com.Charmeetchic.Usuario.model.Usuario;
import com.Charmeetchic.Usuario.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testRegistrarFernando() {
        Usuario usuario = new Usuario();
        usuario.setId(2L);
        usuario.setNombre("Fernando");
        usuario.setApellido("Prueba");
        usuario.setCorreo("fernando@ejemplo.com");
        usuario.setContrasenia("clave123");
        usuario.setRol(Usuario.Rol.COMPRADOR);

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario registrado = usuarioService.registrar(usuario);

        assertNotNull(registrado);
        assertEquals("Fernando", registrado.getNombre());
        assertEquals("fernando@ejemplo.com", registrado.getCorreo());
        assertEquals(Usuario.Rol.COMPRADOR, registrado.getRol());

        verify(usuarioRepository, times(1)).save(usuario);
    }

}