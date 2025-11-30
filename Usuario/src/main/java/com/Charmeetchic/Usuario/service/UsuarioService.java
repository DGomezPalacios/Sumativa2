package com.Charmeetchic.Usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Charmeetchic.Usuario.model.Usuario;
import com.Charmeetchic.Usuario.model.Usuario.Rol;
import com.Charmeetchic.Usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /* Registrar un nuevo usuario. */
    public Usuario registrar(Usuario usuario) {
        if (usuario.getRol() == null) {
            usuario.setRol(Rol.COMPRADOR); // o el que quieras como default
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Login por correo y contraseña en texto plano.
     * Devuelve Optional<Usuario> con el usuario autenticado
     * o Optional.empty() si las credenciales son incorrectas.
     */
    public Optional<Usuario> login(String correo, String contrasenia) {
        Optional<Usuario> user = usuarioRepository.findByCorreo(correo);
        if (user.isPresent() && user.get().getContrasenia().equals(contrasenia)) {
            return user; // login exitoso
        }
        return Optional.empty(); // credenciales incorrectas
    }

    /* Actualizar perfil completo de un usuario existente.*/
    public Usuario actualizarPerfil(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setContrasenia(usuarioActualizado.getContrasenia());
        usuarioExistente.setRol(usuarioActualizado.getRol());

        return usuarioRepository.save(usuarioExistente);
    }

    /* Obtener todos los usuarios (para el panel de administración). */
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /* Obtener un usuario por su ID. */
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    /* Eliminar un usuario por su ID. */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

