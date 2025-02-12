package com.clinica.gestion_clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.gestion_clinica.model.CitaMedica;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

    // Obtener citas de un médico
    List<CitaMedica> findByMedico_IdUsuario(Long idUsuario);

    // Obtener citas de un paciente
    List<CitaMedica> findByPaciente_IdPaciente(Long idPaciente);

    // Verificar si un paciente ya tiene una cita en esa fecha y hora
    boolean existsByPaciente_IdPacienteAndFechaHora(Long idPaciente, String fechaHora);

    // Verificar si un médico ya tiene una cita en esa fecha y hora
    boolean existsByMedico_IdUsuarioAndFechaHora(Long idUsuario, String fechaHora);
}
