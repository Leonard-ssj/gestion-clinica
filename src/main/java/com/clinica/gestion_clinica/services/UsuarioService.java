package com.clinica.gestion_clinica.services;

import java.util.List;
import java.util.Optional;
import com.clinica.gestion_clinica.model.Usuario;
import com.clinica.gestion_clinica.model.dto.UsuarioRequest;

public interface UsuarioService {
    Usuario guardarUsuario(String nombreUsuario, String contraseña, Long rolId);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> obtenerMedicos();  // Agregar esta función a la interfaz
    List<Usuario> obtenerTodosLosUsuarios();
    void eliminarUsuario(Long id);
    Usuario actualizarUsuario(Long id, UsuarioRequest usuarioRequest);
    List<Usuario> obtenerUsuariosPorRol(Long idRol);

}
