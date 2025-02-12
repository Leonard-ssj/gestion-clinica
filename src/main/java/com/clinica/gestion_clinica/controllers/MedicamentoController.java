package com.clinica.gestion_clinica.controllers;

import com.clinica.gestion_clinica.model.Medicamento;
import com.clinica.gestion_clinica.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    /**
     * 
     * METODOS GET
     */

    @GetMapping
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        return ResponseEntity.ok(medicamentoService.listarMedicamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obtenerMedicamentoPorId(@PathVariable Long id) {
        Optional<Medicamento> medicamento = medicamentoService.obtenerMedicamentoPorId(id);
        return medicamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 
     * METODOS POST
     */

    @PostMapping
    public ResponseEntity<Medicamento> agregarMedicamento(@RequestBody Medicamento medicamento) {
        Medicamento nuevoMedicamento = medicamentoService.guardarMedicamento(medicamento);
        return ResponseEntity.ok(nuevoMedicamento);
    }

    /**
     * 
     * METODOS PUT
     */

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> actualizarMedicamento(@PathVariable Long id,
            @RequestBody Medicamento medicamento) {
        Medicamento medicamentoActualizado = medicamentoService.actualizarMedicamento(id, medicamento);
        return ResponseEntity.ok(medicamentoActualizado);
    }

    /**
     * 
     * METODOS DELETE
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedicamento(@PathVariable Long id) {
        medicamentoService.eliminarMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}
