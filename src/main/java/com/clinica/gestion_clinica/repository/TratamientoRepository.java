package com.clinica.gestion_clinica.repository;

import com.clinica.gestion_clinica.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    
    List<Tratamiento> findByHistoriaClinica_IdHistoria(Long idHistoria);
    Optional<Tratamiento> findByIdTratamientoAndMedicamentos_IdMedicamento(Long idTratamiento, Long idMedicamento);

}
