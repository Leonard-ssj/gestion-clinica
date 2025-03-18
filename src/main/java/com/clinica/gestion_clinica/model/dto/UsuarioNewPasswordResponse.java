package com.clinica.gestion_clinica.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioNewPasswordResponse {
    private Long idUsuario;
    private String nombreUsuario;
    private Long rolId;
    private boolean requiereNuevaContraseña;

    public UsuarioNewPasswordResponse(Long idUsuario, String nombreUsuario, Long rolId, boolean requiereNuevaContraseña) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.rolId = rolId;
        this.requiereNuevaContraseña = requiereNuevaContraseña;
    }
}
