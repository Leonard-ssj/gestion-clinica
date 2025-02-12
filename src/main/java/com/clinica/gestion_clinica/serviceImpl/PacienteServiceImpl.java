package com.clinica.gestion_clinica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.gestion_clinica.model.Paciente;
import com.clinica.gestion_clinica.repository.PacienteRepository;
import com.clinica.gestion_clinica.services.PacienteService;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Long id, Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(id);
        if (pacienteExistente.isPresent()) {
            paciente.setIdPaciente(id);
            return pacienteRepository.save(paciente);
        } else {
            throw new RuntimeException("Paciente no encontrado");
        }
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public List<Paciente> buscarPorNombre(String nombre) {
        return pacienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
