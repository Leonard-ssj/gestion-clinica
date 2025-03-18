package com.clinica.gestion_clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.gestion_clinica.services.EstadisticasService;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private EstadisticasService estadisticasService;

    @GetMapping("/total-pacientes")
    public ResponseEntity<Long> obtenerTotalPacientes() {
        return ResponseEntity.ok(estadisticasService.contarPacientes());
    }

    @GetMapping("/total-citas-programadas")
    public ResponseEntity<Long> obtenerTotalCitasProgramadas() {
        return ResponseEntity.ok(estadisticasService.contarCitasProgramadas());
    }

    @GetMapping("/total-medicamentos")
    public ResponseEntity<Long> obtenerTotalMedicamentos() {
        return ResponseEntity.ok(estadisticasService.contarMedicamentos());
    }

    @GetMapping("/total-usuarios")
    public ResponseEntity<Long> obtenerTotalUsuarios() {
        return ResponseEntity.ok(estadisticasService.contarUsuarios());
    }
}
