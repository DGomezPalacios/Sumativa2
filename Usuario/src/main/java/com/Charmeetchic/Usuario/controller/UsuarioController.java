package com.Charmeetchic.Usuario.controller;

import com.Charmeetchic.Usuario.jwt.JwtUtil;
import com.Charmeetchic.Usuario.model.AuthResponse;
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
    // LISTAR todos los usuarios
    // ===========================
    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // ===========================
    // OBTENER usuario por ID
    // ===========================
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ===========================
    // CREAR nuevo usuario
    // ===========================
    @PostMapping
    @Operation(summary = "Registrar nuevo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrar(usuario);
        return ResponseEntity.ok(nuevo);
    }

    // ===========================
    // ACTUALIZAR usuario
    // ===========================
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarPerfil(id, usuario);
        return ResponseEntity.ok(actualizado);
    }

    // ===========================
    // ELIMINAR usuario
    // ===========================
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado con éxito")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // ===========================
    // LOGIN con JWT
    // ===========================
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
        @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> autenticado =
                usuarioService.login(loginRequest.getCorreo(), loginRequest.getContrasenia());

        if (autenticado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Credenciales incorrectas");
        }

        Usuario usuario = autenticado.get();
        String token = jwtUtil.generateToken(usuario);

        AuthResponse resp = new AuthResponse();
        resp.setToken(token);
        resp.setUsuario(usuario);

        return ResponseEntity.ok(resp);
    }
}
