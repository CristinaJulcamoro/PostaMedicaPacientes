/**
 * @file: CitasMedicasService.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:21:04
 */
package edu.unc.PostaMedicaPacientes.services;

import java.util.List;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.dto.CitaMedicaDTO;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;

/**
 * 
 */
public interface CitasMedicasService {
	List<CitaMedica> listarTodas();
    CitaMedica buscarPorId(Long id) throws EntityNotFoundException;
    CitaMedica registrarCita(CitaMedicaDTO citaMedicaDTO) throws EntityNotFoundException, IllegalOperationException;
    CitaMedica actualizarCita(Long id, CitaMedicaDTO citaMedicaDTO) throws EntityNotFoundException, IllegalOperationException;
    void eliminarCita(Long id) throws EntityNotFoundException, IllegalOperationException;

    // Métodos adicionales
    void generarReporteEstadistico();
    List<String> consultarDisponibilidadMedicos();
    void alertasInteraccionesMedicamentosas(Long pacienteId) throws EntityNotFoundException;
}
