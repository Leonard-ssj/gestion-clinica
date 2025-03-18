package com.clinica.gestion_clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.gestion_clinica.model.Rol;
import com.clinica.gestion_clinica.model.Usuario;
import com.clinica.gestion_clinica.model.dto.UsuarioNewPasswordResponse;
import com.clinica.gestion_clinica.model.dto.UsuarioRequest;
import com.clinica.gestion_clinica.model.dto.UsuarioResponse;
import com.clinica.gestion_clinica.repository.RolRepository;
import com.clinica.gestion_clinica.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    /**
     * 
     * METODOS GET
     */

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> obtenerRoles() {
        return ResponseEntity.ok(rolRepository.findAll());
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<UsuarioResponse>> obtenerMedicos() {
        return ResponseEntity.ok(usuarioService.obtenerMedicos());
    }

    @GetMapping("/rol/{idRol}")
    public ResponseEntity<List<UsuarioResponse>> obtenerUsuariosPorRol(@PathVariable Long idRol) {
        return ResponseEntity.ok(usuarioService.obtenerUsuariosPorRol(idRol));
    }

    @GetMapping("/newpassword/{id}")
    public ResponseEntity<UsuarioNewPasswordResponse> obtenerUsuarioConNuevaContraseña(@PathVariable Long id) {
        Optional<UsuarioNewPasswordResponse> usuario = usuarioService.obtenerUsuarioConNuevaContraseña(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 
     * METODOS POST
     */

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuarioRequest.getNombreUsuario(),
                usuarioRequest.getContraseña(),
                usuarioRequest.getRolId());
        return ResponseEntity.ok(nuevoUsuario);
    }

    /**
     * 
     * METODOS PUT
     */

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,
            @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioRequest);
        return ResponseEntity.ok(usuarioActualizado);
    }

    /**
     * 
     * METODOS DELETE
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
