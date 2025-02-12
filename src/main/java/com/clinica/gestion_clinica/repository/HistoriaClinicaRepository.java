package com.clinica.gestion_clinica.repository;

import com.clinica.gestion_clinica.model.HistoriaClinica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    HistoriaClinica findByPaciente_IdPaciente(Long idPaciente);
    List<HistoriaClinica> findAll();
}
