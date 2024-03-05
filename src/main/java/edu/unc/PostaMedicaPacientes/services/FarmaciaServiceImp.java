/**
 * @file: FarmaciaServiceImp.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:23:37
 */
package edu.unc.PostaMedicaPacientes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.PostaMedicaPacientes.domain.Medicamento;
import edu.unc.PostaMedicaPacientes.domain.Paciente;
import edu.unc.PostaMedicaPacientes.dto.MedicamentoDTO;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;
import edu.unc.PostaMedicaPacientes.repositories.MedicamentoRepository;
import edu.unc.PostaMedicaPacientes.repositories.PacienteRepository;

/**
 * 
 */
public class FarmaciaServiceImp implements FarmaciaService{

	@Autowired
    private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;

	 @Autowired
	    private ModelMapper modelMapper;
	
    @Override
    @Transactional(readOnly = true)
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepository.findAll();
    }

	@Override
	@Transactional(readOnly = true)
	public Medicamento buscarMedicamentoPorId(Long id) throws EntityNotFoundException{
	    return medicamentoRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Medicamento no encontrado con ID: " + id));
	}

	@Override
	public void registrarMedicamento(MedicamentoDTO medicamentoDTO) {
		try {
	        if (medicamentoRepository.existsByNombre(medicamentoDTO.getNombre())) {
	            throw new IllegalOperationException("Ya existe un medicamento con el nombre proporcionado.");
	        }
	        Medicamento nuevoMedicamento = modelMapper.map(medicamentoDTO, Medicamento.class);
	        medicamentoRepository.save(nuevoMedicamento);
	    } catch (IllegalOperationException ex) {
	        ex.printStackTrace();
	    }	
	}

	@Override
	public void actualizarMedicamento(Long id, MedicamentoDTO medicamentoDTO) throws EntityNotFoundException{
		Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicamento no encontrado con ID: " + id));
        medicamento.setNombre(medicamentoDTO.getNombre());
        medicamento.setDescripcion(medicamentoDTO.getDescripcion());

        medicamentoRepository.save(medicamento);		
	}

	@Override
	public void eliminarMedicamento(Long id) throws EntityNotFoundException{
		Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicamento no encontrado con ID: " + id));
        medicamentoRepository.delete(medicamento);
	}

	@Override
	public List<String> consultarDisponibilidadMedicamentos() {
		List<Medicamento> medicamentos = medicamentoRepository.findAll();
	    List<String> nombresMedicamentos = new ArrayList<>();
	    for (Medicamento medicamento : medicamentos) {
	        nombresMedicamentos.add(medicamento.getNombre()); 
	    }
	    return nombresMedicamentos;
    }

	
	@Override
	public void alertasInteraccionesMedicamentosas(Long pacienteId) throws EntityNotFoundException{
		Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + pacienteId));

        if (tieneInteraccionesMedicamentosas(paciente)) {
            System.out.println("Alerta: El paciente " + paciente.getNombre() + " tiene posibles interacciones medicamentosas.");
        } else {
            System.out.println("El paciente " + paciente.getNombre() + " no tiene interacciones medicamentosas conocidas.");
        }		
	}
	 private boolean tieneInteraccionesMedicamentosas(Paciente paciente) {
	        return paciente.getHistorialMedico().getRecetasMedicas().size() > 1;
	    }

}
