package com.Charmeetchic.Usuario.controller;

import com.Charmeetchic.Usuario.jwt.JwtUtil;
import com.Charmeetchic.Usuario.model.AuthResponse;
import com.Charmeetchic.Usuario.model.LoginRequest;
import com.Charmeetchic.Usuario.model.Usuario;
import com.Charmeetchic.Usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuarios", description = "CRUD de usuarios sin HATEOAS")
@CrossOrigin(origins = { "http://localhost:3000", "https://charmeet-chic.vercel.app" })
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    // ===========================
    // LISTAR
    // ===========================
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    // ===========================
    // OBTENER POR ID
    // ===========================
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ===========================
    // CREAR
    // ===========================
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrar(usuario);
        return ResponseEntity.ok(nuevo);
    }

    // ===========================
    // ACTUALIZAR
    // ===========================
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        Usuario actualizado = usuarioService.actualizarPerfil(id, usuario);
        return ResponseEntity.ok(actualizado);
    }

    // ===========================
    // ELIMINAR
    // ===========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // ===========================
    // LOGIN CORREGIDO
    // ===========================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Optional<Usuario> autenticado =
                usuarioService.login(loginRequest.getCorreo(), loginRequest.getContrasenia());

        if (autenticado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Credenciales incorrectas");
        }

        Usuario usuario = autenticado.get();

        // Evita NPE para el rol en el token
        if (usuario.getRol() == null) {
            usuario.setRol(Usuario.Rol.COMPRADOR);
        }

        String token = jwtUtil.generateToken(usuario);

        AuthResponse resp = new AuthResponse(token, usuario);

        return ResponseEntity.ok(resp);
    }
}
