package com.clinica.gestion_clinica.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private Long idUsuario;
    private String nombreUsuario;
    private Long rolId;

    public UsuarioResponse(Long idUsuario, String nombreUsuario, Long rolId) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.rolId = rolId;
    }
}
