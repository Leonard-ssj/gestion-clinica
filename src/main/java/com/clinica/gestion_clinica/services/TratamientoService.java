package com.clinica.gestion_clinica.services;

import com.clinica.gestion_clinica.model.Tratamiento;
import java.util.List;
import java.util.Optional;

public interface TratamientoService {
    Optional<Tratamiento> obtenerTratamientoPorId(Long id);
    Tratamiento guardarTratamiento(Tratamiento tratamiento);
    List<Tratamiento> obtenerTratamientosPorHistoriaClinica(Long historiaClinicaId);
    Tratamiento agregarMedicamentosATratamiento(Long idTratamiento, List<Long> idMedicamentos);
    void eliminarMedicamentoDeTratamiento(Long idTratamiento, Long idMedicamento);
    Tratamiento actualizarTratamiento(Long id, Tratamiento tratamientoActualizado);

}

