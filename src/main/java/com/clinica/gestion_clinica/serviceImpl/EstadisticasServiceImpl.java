package com.clinica.gestion_clinica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.gestion_clinica.repository.CitaMedicaRepository;
import com.clinica.gestion_clinica.repository.MedicamentoRepository;
import com.clinica.gestion_clinica.repository.PacienteRepository;
import com.clinica.gestion_clinica.repository.UsuarioRepository;
import com.clinica.gestion_clinica.services.EstadisticasService;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Long contarPacientes() {
        return pacienteRepository.count();
    }

    @Override
    public Long contarCitasProgramadas() {
        return citaMedicaRepository.countByEstado("Confirmada");
    }

    @Override
    public Long contarMedicamentos() {
        return medicamentoRepository.count();
    }

    @Override
    public Long contarUsuarios() {
        return usuarioRepository.count();
    }
}
