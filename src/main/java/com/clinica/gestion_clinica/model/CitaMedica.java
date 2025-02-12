package com.clinica.gestion_clinica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "citas_medicas")
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Usuario medico; // Ahora referencia a Usuario

    @Column(nullable = false)
    private String fechaHora;

    @Column(nullable = false)
    private String estado = "Confirmada"; // Por defecto, siempre se crea como Confirmada

    private String motivoConsulta;
}
