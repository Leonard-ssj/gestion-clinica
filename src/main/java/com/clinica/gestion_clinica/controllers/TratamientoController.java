package com.clinica.gestion_clinica.controllers;

import com.clinica.gestion_clinica.model.Tratamiento;
import com.clinica.gestion_clinica.services.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    /**
     * 
     * METODOS GET
     */

    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> obtenerTratamientoPorId(@PathVariable Long id) {
        Optional<Tratamiento> tratamiento = tratamientoService.obtenerTratamientoPorId(id);
        return tratamiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 
     * METODOS POST
     */

    @PostMapping
    public ResponseEntity<Tratamiento> crearTratamiento(@RequestBody Tratamiento tratamiento) {
        Tratamiento nuevoTratamiento = tratamientoService.guardarTratamiento(tratamiento);
        return ResponseEntity.ok(nuevoTratamiento);
    }

    @PostMapping("/{id}/medicamentos")
    public ResponseEntity<Tratamiento> agregarMedicamentos(@PathVariable Long id,
            @RequestBody List<Long> idMedicamentos) {
        Tratamiento tratamientoActualizado = tratamientoService.agregarMedicamentosATratamiento(id, idMedicamentos);
        return ResponseEntity.ok(tratamientoActualizado);
    }

    /**
     * 
     * METODOS PUT
     */

    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> actualizarTratamiento(@PathVariable Long id,
            @RequestBody Tratamiento tratamientoActualizado) {
        Tratamiento tratamientoModificado = tratamientoService.actualizarTratamiento(id, tratamientoActualizado);
        return ResponseEntity.ok(tratamientoModificado);
    }

    /**
     * 
     * METODOS DELETE
     */

    @DeleteMapping("/{idTratamiento}/medicamentos/{idMedicamento}")
    public ResponseEntity<String> eliminarMedicamento(@PathVariable Long idTratamiento,
            @PathVariable Long idMedicamento) {
        tratamientoService.eliminarMedicamentoDeTratamiento(idTratamiento, idMedicamento);
        return ResponseEntity.ok("Medicamento eliminado correctamente del tratamiento.");
    }

}
