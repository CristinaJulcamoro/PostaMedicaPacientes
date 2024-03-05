/**
 * @file: DiagnosticoTratamientoServiceImp.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:22:41
 */
package edu.unc.PostaMedicaPacientes.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.PostaMedicaPacientes.domain.Diagnostico;
import edu.unc.PostaMedicaPacientes.domain.RecetaMedica;
import edu.unc.PostaMedicaPacientes.dto.DiagnosticoDTO;
import edu.unc.PostaMedicaPacientes.dto.RecetaMedicaDTO;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;
import edu.unc.PostaMedicaPacientes.exception.ErrorMessage;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;
import edu.unc.PostaMedicaPacientes.repositories.DiagnosticoRepository;
import edu.unc.PostaMedicaPacientes.repositories.RecetaMedicaRepository;

/**
 * 
 */
public class DiagnosticoTratamientoServiceImp implements DiagnosticoTratamientoService{

	@Autowired
    private DiagnosticoRepository diagnosticoRepository;

	@Override
	@Transactional(readOnly = true)
    public List<Diagnostico> listarTodos() {
        return diagnosticoRepository.findAll();
    }

	@Override
	@Transactional(readOnly = true)
    public Diagnostico buscarPorId(Long id) throws EntityNotFoundException {
        return diagnosticoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnóstico y Tratamiento no encontrado con ID: " + id));
    }

	@Override
	public Diagnostico registrarDiagnosticoTratamiento(Diagnostico diagnostico)
			throws EntityNotFoundException, IllegalOperationException {
		if (diagnostico.getId() != null) {
	        throw new IllegalOperationException("No se puede registrar un diagnóstico con un ID existente.");
	    }
	    Diagnostico nuevoDiagnostico = diagnosticoRepository.save(diagnostico);
	    return nuevoDiagnostico;
	}

	@Override
	public Diagnostico actualizarDiagnosticoTratamiento(Long id, Diagnostico diagnosticoTratamientoDTO)
			throws EntityNotFoundException, IllegalOperationException {
		if (id == null || !diagnosticoRepository.existsById(id)) {
	        throw new EntityNotFoundException("Diagnóstico y Tratamiento no encontrado con ID: " + id);
	    }
	    if (!id.equals(diagnosticoTratamientoDTO.getId())) {
	        throw new IllegalOperationException("No se puede cambiar el ID del diagnóstico.");
	    }

	    Diagnostico actualizado = diagnosticoRepository.save(diagnosticoTratamientoDTO);

	    return actualizado;
	}

	@Override
	public void eliminarDiagnosticoTratamiento(Long id) throws EntityNotFoundException, IllegalOperationException {

	if (id == null) {
		throw new IllegalArgumentException("El ID no puede ser nulo.");
	}
	Optional<Diagnostico> optionalDiagnostico = diagnosticoRepository.findById(id);

    if (optionalDiagnostico.isEmpty()) {
        throw new EntityNotFoundException("Diagnóstico y Tratamiento no encontrado con ID: " + id);
    }

    Diagnostico diagnostico = optionalDiagnostico.get();
    if (!diagnostico.getRecetasMedicas().isEmpty()) {
        throw new IllegalOperationException("No se puede eliminar el diagnóstico ya que tiene recetas médicas asociadas.");
    }

    diagnosticoRepository.deleteById(id);		
	}

	@Override
	public List<String> consultarDisponibilidadMedicamentosEnFarmacia() throws IllegalOperationException, EntityNotFoundException {
	    if (!farmaciaAbierta()) {
	        throw new IllegalOperationException("La farmacia está cerrada en este momento.");
	    }

	    if (!hayMedicamentosEnInventario()) {
	        throw new EntityNotFoundException("No hay medicamentos disponibles en la farmacia en este momento.");
	    }
	    List<String> medicamentosDisponibles = consultarMedicamentosEnInventario();
	    return medicamentosDisponibles;
	}

	private boolean farmaciaAbierta() {
	    return true; 
	}

	private boolean hayMedicamentosEnInventario() {
	    return true; 
	}

	private List<String> consultarMedicamentosEnInventario() throws IllegalOperationException{
	    if (inventarioVacio()) {
	        throw new IllegalOperationException("El inventario de medicamentos está vacío.");
	    }

	    return List.of("Medicamento1", "Medicamento2"); 
	}
	private boolean inventarioVacio() {
	    return false; 
	}

}
