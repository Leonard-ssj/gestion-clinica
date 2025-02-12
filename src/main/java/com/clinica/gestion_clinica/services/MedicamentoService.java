package com.clinica.gestion_clinica.services;

import com.clinica.gestion_clinica.model.Medicamento;
import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    List<Medicamento> listarMedicamentos();
    Optional<Medicamento> obtenerMedicamentoPorId(Long id);
    Medicamento guardarMedicamento(Medicamento medicamento);
    Medicamento actualizarMedicamento(Long id, Medicamento medicamento);
    void eliminarMedicamento(Long id);
}
