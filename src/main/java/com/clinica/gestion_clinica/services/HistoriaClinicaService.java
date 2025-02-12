package com.clinica.gestion_clinica.services;

import com.clinica.gestion_clinica.model.HistoriaClinica;

import java.util.List;
import java.util.Optional;

public interface HistoriaClinicaService {
    Optional<HistoriaClinica> obtenerHistoriaPorId(Long id);
    HistoriaClinica crearHistoria(HistoriaClinica historiaClinica);
    HistoriaClinica actualizarHistoria(Long id, HistoriaClinica historiaActualizada);
    List<HistoriaClinica> obtenerTodasLasHistorias();
}
