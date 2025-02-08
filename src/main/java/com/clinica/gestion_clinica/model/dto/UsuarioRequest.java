package com.clinica.gestion_clinica.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    private String nombreUsuario;
    private String contraseña;
    private Long rolId;
}
