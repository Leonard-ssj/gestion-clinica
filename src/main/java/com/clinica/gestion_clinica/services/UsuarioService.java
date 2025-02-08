package com.clinica.gestion_clinica.services;

import java.util.Optional;

import com.clinica.gestion_clinica.model.Usuario;

public interface UsuarioService {
    Usuario guardarUsuario(Usuario usuario);
    Optional<Usuario> buscarPorNombreUsuario(String nombreUsuario);
}