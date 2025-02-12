package com.clinica.gestion_clinica.services;

import java.util.List;
import java.util.Optional;
import com.clinica.gestion_clinica.model.Paciente;

public interface PacienteService {
    List<Paciente> listarPacientes();
    Optional<Paciente> obtenerPacientePorId(Long id);
    Paciente guardarPaciente(Paciente paciente);
    Paciente actualizarPaciente(Long id, Paciente paciente);
    void eliminarPaciente(Long id);
    List<Paciente> buscarPorNombre(String nombre);
}
