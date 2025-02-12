package com.clinica.gestion_clinica.serviceImpl;

import com.clinica.gestion_clinica.model.Medicamento;
import com.clinica.gestion_clinica.repository.MedicamentoRepository;
import com.clinica.gestion_clinica.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Optional<Medicamento> obtenerMedicamentoPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Override
    public Medicamento guardarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public Medicamento actualizarMedicamento(Long id, Medicamento medicamentoActualizado) {
        return medicamentoRepository.findById(id).map(medicamento -> {
            medicamento.setNombre(medicamentoActualizado.getNombre());
            medicamento.setDescripcion(medicamentoActualizado.getDescripcion());
            medicamento.setContraindicaciones(medicamentoActualizado.getContraindicaciones());
            medicamento.setDosisRecomendada(medicamentoActualizado.getDosisRecomendada());
            return medicamentoRepository.save(medicamento);
        }).orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + id));
    }

    @Override
    public void eliminarMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }
}
