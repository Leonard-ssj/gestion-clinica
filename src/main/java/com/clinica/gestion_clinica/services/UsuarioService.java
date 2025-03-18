package com.clinica.gestion_clinica.services;

import java.util.List;
import java.util.Optional;
import com.clinica.gestion_clinica.model.Usuario;
import com.clinica.gestion_clinica.model.dto.UsuarioNewPasswordResponse;
import com.clinica.gestion_clinica.model.dto.UsuarioRequest;
import com.clinica.gestion_clinica.model.dto.UsuarioResponse;

public interface UsuarioService {
    Usuario guardarUsuario(String nombreUsuario, String contraseña, Long rolId);
    Optional<Usuario> buscarPorId(Long id);
    List<UsuarioResponse> obtenerMedicos();  // Agregar esta función a la interfaz
    List<UsuarioResponse> obtenerTodosLosUsuarios();
    void eliminarUsuario(Long id);
    Usuario actualizarUsuario(Long id, UsuarioRequest usuarioRequest);
    List<UsuarioResponse> obtenerUsuariosPorRol(Long idRol);

    // Nuevo método
    Optional<UsuarioNewPasswordResponse> obtenerUsuarioConNuevaContraseña(Long id);

}
