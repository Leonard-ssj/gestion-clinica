package com.clinica.gestion_clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.gestion_clinica.model.Paciente;
import com.clinica.gestion_clinica.services.PacienteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    /**
     * 
     * METODOS GET
     */

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPaciente(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.obtenerPacientePorId(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Paciente>> buscarPacientes(@PathVariable String nombre) {
        return ResponseEntity.ok(pacienteService.buscarPorNombre(nombre));
    }

    /**
     * 
     * METODOS POST
     */

    @PostMapping
    public ResponseEntity<Paciente> agregarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    /**
     * 
     * METODOS PUT
     */

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, paciente));
    }

    /**
     * 
     * METODOS DELETE - SOLO PARA ADMINISTRADORES
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        // Aquí deberíamos validar que el usuario actual es ADMINISTRADOR
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
