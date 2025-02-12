package com.clinica.gestion_clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.gestion_clinica.model.Paciente;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
}
