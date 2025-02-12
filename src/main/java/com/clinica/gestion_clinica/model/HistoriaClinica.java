package com.clinica.gestion_clinica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "historias_clinicas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoria;

    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    private String notasMedicas;
    private String fechaUltimaActualizacion;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("historiaClinica")  // Evita bucles infinitos en serialización
    private List<Tratamiento> tratamientos;
}



