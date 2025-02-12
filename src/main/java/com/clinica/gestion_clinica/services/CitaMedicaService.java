package com.clinica.gestion_clinica.services;

import java.util.List;
import java.util.Optional;

import com.clinica.gestion_clinica.model.CitaMedica;

public interface CitaMedicaService {

    CitaMedica crearCita(CitaMedica cita);

    Optional<CitaMedica> obtenerCitaPorId(Long id);

    List<CitaMedica> obtenerTodasLasCitas();

    CitaMedica cancelarCita(Long id);

    CitaMedica actualizarFechaCita(Long id, String nuevaFecha);

    List<CitaMedica> obtenerCitasPorMedico(Long idMedico);

    List<CitaMedica> obtenerCitasPorPaciente(Long idPaciente);
}
