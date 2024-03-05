/**
 * @file: RegistroPacientesService.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:20:20
 */
package edu.unc.PostaMedicaPacientes.services;

import java.util.List;

import edu.unc.PostaMedicaPacientes.domain.Paciente;
import edu.unc.PostaMedicaPacientes.dto.PacienteDTO;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;

/**
 * 
 */
public interface RegistroPacientesService {

	 List<Paciente> listarTodos();

	    Paciente buscarPorId(Long id) throws EntityNotFoundException;

	    Paciente registrarPaciente(PacienteDTO pacienteDTO) throws IllegalOperationException;

	    Paciente actualizarPaciente(Long id, PacienteDTO pacienteDTO) throws EntityNotFoundException, IllegalOperationException;

	    void eliminarPaciente(Long id) throws EntityNotFoundException, IllegalOperationException;

	    void asignarExpedienteMedico(Long pacienteId) throws EntityNotFoundException, IllegalOperationException;

	
	void generarReporteEstadistico();
    List<String> consultarDisponibilidadMedicos();
    void alertasInteraccionesMedicamentosas(Long pacienteId) throws EntityNotFoundException;
}