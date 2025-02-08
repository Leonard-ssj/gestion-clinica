package com.clinica.gestion_clinica.services;

import java.util.List;
import java.util.Optional;
import com.clinica.gestion_clinica.model.Usuario;

public interface UsuarioService {
    Usuario guardarUsuario(String nombreUsuario, String contraseña, Long rolId);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> obtenerMedicos();  // Agregar esta función a la interfaz
}
