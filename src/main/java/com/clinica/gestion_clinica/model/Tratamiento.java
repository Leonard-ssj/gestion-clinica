package com.clinica.gestion_clinica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "tratamientos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratamiento;

    @ManyToOne
    @JoinColumn(name = "historia_clinica_id", nullable = false)
    @JsonIgnoreProperties("tratamientos") // Evita bucles de serialización
    private HistoriaClinica historiaClinica;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Usuario medico;

    private String fechaInicio;
    private String fechaFin;
    private String indicaciones;

    @ManyToMany
    @JoinTable(
        name = "tratamiento_medicamentos",
        joinColumns = @JoinColumn(name = "tratamiento_id"),
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<Medicamento> medicamentos;
}

