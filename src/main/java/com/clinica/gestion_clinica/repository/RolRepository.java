package com.clinica.gestion_clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.gestion_clinica.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
