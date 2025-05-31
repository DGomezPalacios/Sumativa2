package com.Charmeetchic.Usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Charmeetchic.Usuario.model.Usuario;
import com.Charmeetchic.Usuario.repository.UsuarioRepository;


@Service

public class UsuarioService {
    @Autowired

    private UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(String correo, String contrasena) {
        Optional<Usuario> user = usuarioRepository.findByCorreo(correo);
        if (user.isPresent() && user.get().getContrasenia().equals(contrasena)) {
            return user; // login exitoso
        }
        return Optional.empty(); //credenciales incorectas
    }

    public Usuario actualizarPerfil(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> getAllUsuarios() {
    return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    

}
