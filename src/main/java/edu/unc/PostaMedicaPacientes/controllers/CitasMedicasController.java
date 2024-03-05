/**
 * @file: CitasMedicasController.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 00:38:02
 */
package edu.unc.PostaMedicaPacientes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.dto.CitaMedicaDTO;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;
import edu.unc.PostaMedicaPacientes.services.CitasMedicasService;
import jakarta.persistence.EntityNotFoundException;
/**
 * 
 */
@RestController
@RequestMapping("/api/citas")
public class CitasMedicasController {

	@Autowired
    private CitasMedicasService citasMedicasService;
	
	public CitasMedicasController(CitasMedicasService citasMedicasService) {
        this.citasMedicasService = citasMedicasService;
    }

    @GetMapping("/todas")
    public ResponseEntity<List<CitaMedica>> listarTodasCitas() {
        List<CitaMedica> citas = citasMedicasService.listarTodas();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> buscarCitaPorId(@PathVariable Long id) throws EntityNotFoundException {
    	try {
            CitaMedica cita = citasMedicasService.buscarPorId(id);
            return new ResponseEntity<>(cita, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<CitaMedica> registrarCita(@RequestBody CitaMedicaDTO citaMedicaDTO) {
    	try {
            CitaMedica nuevaCita = citasMedicasService.registrarCita(citaMedicaDTO);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalOperationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> actualizarCita(@PathVariable Long id, @RequestBody CitaMedicaDTO citaMedicaDTO) {
    	try {
            CitaMedica citaActualizada = citasMedicasService.actualizarCita(id, citaMedicaDTO);
            return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalOperationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) throws EntityNotFoundException{
    	try {
            citasMedicasService.eliminarCita(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalOperationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generar-reporte")
    public ResponseEntity<String> generarReporteEstadistico() {
        citasMedicasService.generarReporteEstadistico();
        return new ResponseEntity<>("Reporte generado con éxito", HttpStatus.OK);
    }

    @GetMapping("/disponibilidad-medicos")
    public ResponseEntity<List<String>> consultarDisponibilidadMedicos() {
        List<String> disponibilidadMedicos = citasMedicasService.consultarDisponibilidadMedicos();
        return new ResponseEntity<>(disponibilidadMedicos, HttpStatus.OK);
    }

    @GetMapping("/alertas-interacciones-medicamentosas/{pacienteId}")
    public ResponseEntity<String> alertasInteraccionesMedicamentosas(@PathVariable Long pacienteId) {
        try {
            citasMedicasService.alertasInteraccionesMedicamentosas(pacienteId);
            return new ResponseEntity<>("Alertas generadas con éxito", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Paciente no encontrado con el ID proporcionado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la generación de alertas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
