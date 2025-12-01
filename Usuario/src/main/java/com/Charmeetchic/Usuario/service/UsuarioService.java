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

    /* Registrar un nuevo usuario */
    public Usuario registrar(Usuario usuario) {

        // Asignar rol por defecto si no viene
        if (usuario.getRol() == null) {
            usuario.setRol(Rol.COMPRADOR);
        }

        return usuarioRepository.save(usuario);
    }

    /* Login: correo y contraseña */
    public Optional<Usuario> login(String correo, String contrasenia) {

        Optional<Usuario> user = usuarioRepository.findByCorreo(correo);

        if (user.isPresent() &&
            user.get().getContrasenia() != null &&
            user.get().getContrasenia().equals(contrasenia)) {

            return user;
        }

        return Optional.empty();
    }

    /* Actualizar usuario */
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

    /* Listar usuarios */
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /* Buscar por ID */
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    /* Eliminar */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
