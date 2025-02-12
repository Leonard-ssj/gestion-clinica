package com.clinica.gestion_clinica.serviceImpl;

import com.clinica.gestion_clinica.model.HistoriaClinica;
import com.clinica.gestion_clinica.model.Medicamento;
import com.clinica.gestion_clinica.model.Tratamiento;
import com.clinica.gestion_clinica.model.Usuario;
import com.clinica.gestion_clinica.repository.HistoriaClinicaRepository;
import com.clinica.gestion_clinica.repository.MedicamentoRepository;
import com.clinica.gestion_clinica.repository.TratamientoRepository;
import com.clinica.gestion_clinica.repository.UsuarioRepository;
import com.clinica.gestion_clinica.services.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TratamientoServiceImpl implements TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public Optional<Tratamiento> obtenerTratamientoPorId(Long id) {
        return tratamientoRepository.findById(id);
    }

    @Override
    public Tratamiento guardarTratamiento(Tratamiento tratamiento) {
        if (tratamiento.getHistoriaClinica() == null || tratamiento.getHistoriaClinica().getIdHistoria() == null) {
            throw new RuntimeException("Debe proporcionar un ID de historia clínica válido.");
        }

        // Buscar la historia clínica en la base de datos
        Long historiaId = tratamiento.getHistoriaClinica().getIdHistoria();
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(historiaId)
                .orElseThrow(() -> new RuntimeException("Historia clínica no encontrada con ID: " + historiaId));

        // Asociar la historia clínica al tratamiento
        tratamiento.setHistoriaClinica(historiaClinica);

        // Verificar si el médico existe en la base de datos
        if (tratamiento.getMedico() != null && tratamiento.getMedico().getIdUsuario() != null) {
            Long medicoId = tratamiento.getMedico().getIdUsuario();
            Usuario medico = usuarioRepository.findById(medicoId)
                    .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + medicoId));
            tratamiento.setMedico(medico);
        } else {
            throw new RuntimeException("Debe proporcionar un ID de médico válido.");
        }

        // Verificar los medicamentos antes de asignarlos
        if (tratamiento.getMedicamentos() != null && !tratamiento.getMedicamentos().isEmpty()) {
            List<Medicamento> medicamentosValidados = new ArrayList<>();
            for (Medicamento medicamento : tratamiento.getMedicamentos()) {
                Medicamento med = medicamentoRepository.findById(medicamento.getIdMedicamento())
                        .orElseThrow(() -> new RuntimeException(
                                "Medicamento no encontrado con ID: " + medicamento.getIdMedicamento()));
                medicamentosValidados.add(med);
            }
            tratamiento.setMedicamentos(medicamentosValidados);
        }

        // Guardar el tratamiento con todas sus relaciones resueltas
        return tratamientoRepository.save(tratamiento);
    }

    @Override
    public List<Tratamiento> obtenerTratamientosPorHistoriaClinica(Long historiaClinicaId) {
        return tratamientoRepository.findByHistoriaClinica_IdHistoria(historiaClinicaId);
    }

    @Override
    public Tratamiento agregarMedicamentosATratamiento(Long idTratamiento, List<Long> idMedicamentos) {
        Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + idTratamiento));

        // Obtener los medicamentos actuales en el tratamiento
        Set<Long> medicamentosActuales = tratamiento.getMedicamentos().stream()
                .map(Medicamento::getIdMedicamento)
                .collect(Collectors.toSet());

        // Filtrar los medicamentos que ya están en el tratamiento
        List<Medicamento> nuevosMedicamentos = medicamentoRepository.findAllById(idMedicamentos).stream()
                .filter(m -> !medicamentosActuales.contains(m.getIdMedicamento()))
                .toList();

        // Agregar solo los nuevos medicamentos
        tratamiento.getMedicamentos().addAll(nuevosMedicamentos);

        return tratamientoRepository.save(tratamiento);
    }

    @Override
    public void eliminarMedicamentoDeTratamiento(Long idTratamiento, Long idMedicamento) {
        // Buscar el tratamiento
        Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + idTratamiento));

        // Filtrar la lista para eliminar el medicamento específico
        boolean removed = tratamiento.getMedicamentos().removeIf(m -> m.getIdMedicamento().equals(idMedicamento));

        if (!removed) {
            throw new RuntimeException("El medicamento con ID " + idMedicamento + " no está asociado al tratamiento.");
        }

        // Guardar los cambios en la base de datos
        tratamientoRepository.save(tratamiento);
    }

    @Override
    public Tratamiento actualizarTratamiento(Long id, Tratamiento tratamientoActualizado) {
        return tratamientoRepository.findById(id).map(tratamiento -> {
            tratamiento.setFechaInicio(tratamientoActualizado.getFechaInicio());
            tratamiento.setFechaFin(tratamientoActualizado.getFechaFin());
            tratamiento.setIndicaciones(tratamientoActualizado.getIndicaciones());
            return tratamientoRepository.save(tratamiento);
        }).orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + id));
    }

}
