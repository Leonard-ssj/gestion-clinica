package com.clinica.gestion_clinica.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.gestion_clinica.model.CitaMedica;
import com.clinica.gestion_clinica.model.Usuario;
import com.clinica.gestion_clinica.repository.CitaMedicaRepository;
import com.clinica.gestion_clinica.repository.UsuarioRepository;
import com.clinica.gestion_clinica.services.CitaMedicaService;

@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CitaMedica crearCita(CitaMedica cita) {
        // Validar que el médico tiene el rol de 2 (MÉDICO)
        Usuario medico = usuarioRepository.findById(cita.getMedico().getIdUsuario())
                .orElseThrow(
                        () -> new RuntimeException("Médico no encontrado con ID: " + cita.getMedico().getIdUsuario()));

        if (medico.getRol().getIdRol() != 2) {
            throw new RuntimeException("Solo usuarios con rol de MEDICO pueden ser asignados a una cita.");
        }

        // Validar que el paciente no tenga ya una cita en la misma fecha y hora
        if (citaMedicaRepository.existsByPaciente_IdPacienteAndFechaHora(cita.getPaciente().getIdPaciente(),
                cita.getFechaHora())) {
            throw new RuntimeException("El paciente ya tiene una cita programada en este horario.");
        }

        // Validar que el médico no tenga ya una cita en la misma fecha y hora
        if (citaMedicaRepository.existsByMedico_IdUsuarioAndFechaHora(cita.getMedico().getIdUsuario(),
                cita.getFechaHora())) {
            throw new RuntimeException("El médico ya tiene una cita programada en este horario.");
        }

        return citaMedicaRepository.save(cita);
    }

    @Override
    public Optional<CitaMedica> obtenerCitaPorId(Long id) {
        return citaMedicaRepository.findById(id);
    }

    @Override
    public List<CitaMedica> obtenerTodasLasCitas() {
        return citaMedicaRepository.findAll();
    }

    @Override
    public CitaMedica cancelarCita(Long id) {
        return citaMedicaRepository.findById(id).map(cita -> {
            cita.setEstado("Cancelada");
            return citaMedicaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    @Override
    public CitaMedica actualizarFechaCita(Long id, String nuevaFecha) {
        return citaMedicaRepository.findById(id).map(cita -> {
            // Validar que la cita NO esté cancelada
            if ("Cancelada".equals(cita.getEstado())) {
                throw new RuntimeException("No se puede reprogramar una cita cancelada.");
            }

            // Validar que el paciente no tenga ya una cita en la misma fecha y hora
            if (citaMedicaRepository.existsByPaciente_IdPacienteAndFechaHora(cita.getPaciente().getIdPaciente(),
                    nuevaFecha)) {
                throw new RuntimeException("El paciente ya tiene una cita programada en este horario.");
            }

            // Validar que el médico no tenga ya una cita en la misma fecha y hora
            if (citaMedicaRepository.existsByMedico_IdUsuarioAndFechaHora(cita.getMedico().getIdUsuario(),
                    nuevaFecha)) {
                throw new RuntimeException("El médico ya tiene una cita programada en este horario.");
            }

            // Si pasa las validaciones, actualiza la fecha de la cita y la guarda
            cita.setFechaHora(nuevaFecha);
            return citaMedicaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    @Override
    public List<CitaMedica> obtenerCitasPorMedico(Long idMedico) {
        return citaMedicaRepository.findByMedico_IdUsuario(idMedico);
    }

    @Override
    public List<CitaMedica> obtenerCitasPorPaciente(Long idPaciente) {
        return citaMedicaRepository.findByPaciente_IdPaciente(idPaciente);
    }
}
