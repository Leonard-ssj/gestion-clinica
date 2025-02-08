package com.clinica.gestion_clinica.services;

import java.util.Optional;

import com.clinica.gestion_clinica.model.HistoriaClinica;

public interface HistoriaClinicaService {
    Optional<HistoriaClinica> obtenerHistoriaPorPaciente(Long pacienteId);
    HistoriaClinica guardarHistoria(HistoriaClinica historiaClinica);
}
