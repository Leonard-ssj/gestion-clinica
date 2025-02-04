package com.clinica.gestion_clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.gestion_clinica.model.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
}
