package com.clinica.gestion_clinica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamento;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;
    private String contraindicaciones;
    private String dosisRecomendada;
}
