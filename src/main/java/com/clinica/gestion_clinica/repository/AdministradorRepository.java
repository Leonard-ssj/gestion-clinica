package com.clinica.gestion_clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.gestion_clinica.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}