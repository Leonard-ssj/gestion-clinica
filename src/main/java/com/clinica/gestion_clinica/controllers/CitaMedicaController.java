package com.clinica.gestion_clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.gestion_clinica.model.CitaMedica;
import com.clinica.gestion_clinica.services.CitaMedicaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping
    public ResponseEntity<List<CitaMedica>> listarCitas() {
        return ResponseEntity.ok(citaMedicaService.listarCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> obtenerCita(@PathVariable Long id) {
        Optional<CitaMedica> cita = citaMedicaService.obtenerCitaPorId(id);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CitaMedica> agregarCita(@RequestBody CitaMedica cita) {
        return ResponseEntity.ok(citaMedicaService.guardarCita(cita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        citaMedicaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }
}
