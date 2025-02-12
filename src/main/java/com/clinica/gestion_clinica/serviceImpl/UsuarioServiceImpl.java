package com.clinica.gestion_clinica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.gestion_clinica.model.Rol;
import com.clinica.gestion_clinica.model.Usuario;
import com.clinica.gestion_clinica.model.dto.UsuarioRequest;
import com.clinica.gestion_clinica.repository.RolRepository;
import com.clinica.gestion_clinica.repository.UsuarioRepository;
import com.clinica.gestion_clinica.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(String nombreUsuario, String contraseña, Long rolId) {
        Optional<Rol> rol = rolRepository.findById(rolId);
        if (rol.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContraseña(contraseña);
            usuario.setRol(rol.get());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("El rol especificado no existe.");
        }
    }

    @Override
    public List<Usuario> obtenerMedicos() {
        return usuarioRepository.findMedicos();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario actualizarUsuario(Long id, UsuarioRequest usuarioRequest) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombreUsuario(usuarioRequest.getNombreUsuario());
            usuario.setContraseña(usuarioRequest.getContraseña());
            usuario.setRol(rolRepository.findById(usuarioRequest.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado")));
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public List<Usuario> obtenerUsuariosPorRol(Long idRol) {
        return usuarioRepository.findByRolId(idRol);
    }



}
