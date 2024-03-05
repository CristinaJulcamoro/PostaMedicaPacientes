/**
 * @file: DiagnosticoTratamientoService.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:21:20
 */
package edu.unc.PostaMedicaPacientes.services;

import java.util.List;

import edu.unc.PostaMedicaPacientes.domain.Diagnostico;
import edu.unc.PostaMedicaPacientes.domain.RecetaMedica;
import edu.unc.PostaMedicaPacientes.dto.DiagnosticoDTO;
import edu.unc.PostaMedicaPacientes.dto.RecetaMedicaDTO;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;

/**
 * 
 */
public interface DiagnosticoTratamientoService {
	List<Diagnostico> listarTodos();

	Diagnostico buscarPorId(Long id) throws EntityNotFoundException;

	Diagnostico registrarDiagnosticoTratamiento(Diagnostico diagnosticoTratamientoDTO)
            throws EntityNotFoundException, IllegalOperationException;

	Diagnostico actualizarDiagnosticoTratamiento(Long id, Diagnostico diagnosticoTratamientoDTO)
            throws EntityNotFoundException, IllegalOperationException;

    void eliminarDiagnosticoTratamiento(Long id) throws EntityNotFoundException, IllegalOperationException;

    List<String> consultarDisponibilidadMedicamentosEnFarmacia() throws IllegalOperationException, EntityNotFoundException;
}
