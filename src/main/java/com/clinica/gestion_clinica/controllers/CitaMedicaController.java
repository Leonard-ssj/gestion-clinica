package com.clinica.gestion_clinica.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.gestion_clinica.model.CitaMedica;
import com.clinica.gestion_clinica.services.CitaMedicaService;

@RestController
@RequestMapping("/citas")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    /**
     * METODO GET - Obtener todas las citas
     */
    @GetMapping
    public ResponseEntity<List<CitaMedica>> obtenerTodasLasCitas() {
        return ResponseEntity.ok(citaMedicaService.obtenerTodasLasCitas());
    }

    /**
     * METODO GET - Obtener cita por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> obtenerCitaPorId(@PathVariable Long id) {
        Optional<CitaMedica> cita = citaMedicaService.obtenerCitaPorId(id);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<CitaMedica>> obtenerCitasPorMedico(@PathVariable Long idMedico) {
        return ResponseEntity.ok(citaMedicaService.obtenerCitasPorMedico(idMedico));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<CitaMedica>> obtenerCitasPorPaciente(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(citaMedicaService.obtenerCitasPorPaciente(idPaciente));
    }

    /**
     * METODO POST - Crear una nueva cita
     */
    @PostMapping
    public ResponseEntity<CitaMedica> crearCita(@RequestBody CitaMedica cita) {
        CitaMedica nuevaCita = citaMedicaService.crearCita(cita);
        return ResponseEntity.ok(nuevaCita);
    }

    /**
     * 
     * METODOS PUT
     */

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<CitaMedica> cancelarCita(@PathVariable Long id) {
        CitaMedica citaCancelada = citaMedicaService.cancelarCita(id);
        return ResponseEntity.ok(citaCancelada);
    }

    @PutMapping("/{id}/reprogramar")
    public ResponseEntity<CitaMedica> actualizarFecha(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String nuevaFecha = request.get("fechaHora");
        CitaMedica citaActualizada = citaMedicaService.actualizarFechaCita(id, nuevaFecha);
        return ResponseEntity.ok(citaActualizada);
    }

}
