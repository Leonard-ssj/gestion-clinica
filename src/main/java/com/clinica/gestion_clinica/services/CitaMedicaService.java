package com.clinica.gestion_clinica.services;

import java.util.List;
import java.util.Optional;

import com.clinica.gestion_clinica.model.CitaMedica;

public interface CitaMedicaService {
    
    List<CitaMedica> listarCitas();
    Optional<CitaMedica> obtenerCitaPorId(Long id);
    CitaMedica guardarCita(CitaMedica cita);
    void eliminarCita(Long id);
}
