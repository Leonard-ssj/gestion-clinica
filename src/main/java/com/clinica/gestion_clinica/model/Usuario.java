package com.clinica.gestion_clinica.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    @JsonIgnore
    @Column(nullable = false)
    private String contraseña;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol; // Relación con la tabla de roles

    @Column(name = "rol_id", insertable = false, updatable = false)
    private Long rolId;
}

