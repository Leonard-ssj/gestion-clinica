package com.clinica.gestion_clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.gestion_clinica.model.HistoriaClinica;
import com.clinica.gestion_clinica.services.HistoriaClinicaService;

import java.util.Optional;

@RestController
@RequestMapping("/historias")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @GetMapping("/{pacienteId}")
    public ResponseEntity<HistoriaClinica> obtenerHistoria(@PathVariable Long pacienteId) {
        Optional<HistoriaClinica> historia = historiaClinicaService.obtenerHistoriaPorPaciente(pacienteId);
        return historia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoriaClinica> agregarHistoria(@RequestBody HistoriaClinica historiaClinica) {
        return ResponseEntity.ok(historiaClinicaService.guardarHistoria(historiaClinica));
    }
}