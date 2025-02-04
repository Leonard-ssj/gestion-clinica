package com.clinica.gestion_clinica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enfermeras")
public class Enfermera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnfermera;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private String telefono;
    private String correo;
}

