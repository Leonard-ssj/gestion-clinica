package com.clinica.gestion_clinica.repository;

import com.clinica.gestion_clinica.model.dto.UsuarioNewPasswordResponse;
import com.clinica.gestion_clinica.model.dto.UsuarioResponse;
import com.clinica.gestion_clinica.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query("SELECT new com.clinica.gestion_clinica.model.dto.UsuarioResponse(u.idUsuario, u.nombreUsuario, u.rol.idRol) FROM Usuario u")
    List<UsuarioResponse> findAllUsuarios();

    @Query("SELECT new com.clinica.gestion_clinica.model.dto.UsuarioResponse(u.idUsuario, u.nombreUsuario, u.rol.idRol) FROM Usuario u WHERE u.rol.idRol = 2")
    List<UsuarioResponse> findMedicos();

    @Query("SELECT new com.clinica.gestion_clinica.model.dto.UsuarioResponse(u.idUsuario, u.nombreUsuario, u.rol.idRol) FROM Usuario u WHERE u.rol.idRol = :idRol")
    List<UsuarioResponse> findByRolId(@Param("idRol") Long idRol);

    @Query("SELECT new com.clinica.gestion_clinica.model.dto.UsuarioNewPasswordResponse(u.idUsuario, u.nombreUsuario, u.rol.idRol, true) FROM Usuario u WHERE u.idUsuario = :id")
    Optional<UsuarioNewPasswordResponse> findUsuarioWithNewPassword(@Param("id") Long id);
}
