package com.clinica.gestion_clinica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.gestion_clinica.model.HistoriaClinica;
import com.clinica.gestion_clinica.repository.HistoriaClinicaRepository;
import com.clinica.gestion_clinica.services.HistoriaClinicaService;

import java.util.Optional;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public Optional<HistoriaClinica> obtenerHistoriaPorPaciente(Long pacienteId) {
        return historiaClinicaRepository.findById(pacienteId);
    }

    @Override
    public HistoriaClinica guardarHistoria(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }
}
