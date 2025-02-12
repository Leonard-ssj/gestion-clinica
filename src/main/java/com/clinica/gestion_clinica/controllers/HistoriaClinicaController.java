package com.clinica.gestion_clinica.controllers;

import com.clinica.gestion_clinica.model.HistoriaClinica;
import com.clinica.gestion_clinica.services.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historias")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    /**
     * 
     * METODOS GET
     */

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> obtenerHistoriaPorId(@PathVariable Long id) {
        Optional<HistoriaClinica> historia = historiaClinicaService.obtenerHistoriaPorId(id);
        return historia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<HistoriaClinica>> obtenerTodasLasHistorias() {
        List<HistoriaClinica> historias = historiaClinicaService.obtenerTodasLasHistorias();
        return ResponseEntity.ok(historias);
    }

    /**
     * 
     * METODOS POST
     */

    @PostMapping
    public ResponseEntity<HistoriaClinica> crearHistoria(@RequestBody HistoriaClinica historiaClinica) {
        HistoriaClinica nuevaHistoria = historiaClinicaService.crearHistoria(historiaClinica);
        return ResponseEntity.ok(nuevaHistoria);
    }

    /**
     * 
     * METODOS PUT
     */

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> actualizarHistoria(@PathVariable Long id,
            @RequestBody HistoriaClinica historiaActualizada) {
        HistoriaClinica historiaModificada = historiaClinicaService.actualizarHistoria(id, historiaActualizada);
        return ResponseEntity.ok(historiaModificada);
    }

}
