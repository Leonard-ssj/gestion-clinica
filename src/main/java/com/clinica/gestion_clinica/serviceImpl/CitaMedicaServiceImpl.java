package com.clinica.gestion_clinica.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.gestion_clinica.model.CitaMedica;
import com.clinica.gestion_clinica.repository.CitaMedicaRepository;
import com.clinica.gestion_clinica.services.CitaMedicaService;

@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Override
    public List<CitaMedica> listarCitas() {
        return citaMedicaRepository.findAll();
    }

    @Override
    public Optional<CitaMedica> obtenerCitaPorId(Long id) {
        return citaMedicaRepository.findById(id);
    }

    @Override
    public CitaMedica guardarCita(CitaMedica cita) {
        return citaMedicaRepository.save(cita);
    }

    @Override
    public void eliminarCita(Long id) {
        citaMedicaRepository.deleteById(id);
    }
}
