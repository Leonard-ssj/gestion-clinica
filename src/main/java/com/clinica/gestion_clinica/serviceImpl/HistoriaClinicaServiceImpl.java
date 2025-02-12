package com.clinica.gestion_clinica.serviceImpl;

import com.clinica.gestion_clinica.model.HistoriaClinica;
import com.clinica.gestion_clinica.model.Paciente;
import com.clinica.gestion_clinica.repository.HistoriaClinicaRepository;
import com.clinica.gestion_clinica.repository.PacienteRepository;
import com.clinica.gestion_clinica.services.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Optional<HistoriaClinica> obtenerHistoriaPorId(Long id) {
        return historiaClinicaRepository.findById(id);
    }

    @Override
    public HistoriaClinica crearHistoria(HistoriaClinica historiaClinica) {
        if (historiaClinica.getPaciente() == null) {
            throw new RuntimeException("El JSON debe contener un objeto 'paciente' con un 'idPaciente' válido.");
        }

        Long pacienteId = historiaClinica.getPaciente().getIdPaciente();

        if (pacienteId == null) {
            throw new RuntimeException("El campo 'idPaciente' en el JSON no puede ser nulo.");
        }

        // Recuperar paciente de la base de datos
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + pacienteId));

        // Asignar el paciente recuperado
        historiaClinica.setPaciente(paciente);

        // Guardar la historia clínica con el paciente asociado correctamente
        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public HistoriaClinica actualizarHistoria(Long id, HistoriaClinica historiaActualizada) {
        return historiaClinicaRepository.findById(id).map(historia -> {
            historia.setNotasMedicas(historiaActualizada.getNotasMedicas());
            historia.setFechaUltimaActualizacion(historiaActualizada.getFechaUltimaActualizacion());
            return historiaClinicaRepository.save(historia);
        }).orElseThrow(() -> new RuntimeException("Historia clínica no encontrada con ID: " + id));
    }

    @Override
    public List<HistoriaClinica> obtenerTodasLasHistorias() {
        return historiaClinicaRepository.findAll();
    }
}
