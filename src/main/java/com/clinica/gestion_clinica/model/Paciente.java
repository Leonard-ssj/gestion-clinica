package com.clinica.gestion_clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
@JsonIgnoreProperties({"historiaClinica"})  // Evita bucles sin afectar POST
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String telefono;
    private String correo;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private HistoriaClinica historiaClinica;

    public Paciente() {}  // Constructor vacío obligatorio
}


