/**
 * @file: CitasMedicasServiceImp.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:21:53
 */
package edu.unc.PostaMedicaPacientes.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.Medicamento;
import edu.unc.PostaMedicaPacientes.domain.Medico;
import edu.unc.PostaMedicaPacientes.domain.Paciente;
import edu.unc.PostaMedicaPacientes.dto.CitaMedicaDTO;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;
import edu.unc.PostaMedicaPacientes.repositories.CitaMedicaRepository;
import edu.unc.PostaMedicaPacientes.repositories.MedicamentoRepository;
import edu.unc.PostaMedicaPacientes.repositories.MedicoRepository;
import edu.unc.PostaMedicaPacientes.repositories.PacienteRepository;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;


/**
 * 
 */
@Service
public class CitasMedicasServiceImp implements CitasMedicasService {

	@Autowired
    private CitaMedicaRepository citaMedicaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
    private MedicoRepository medicoRepository;
	
	@Override
    @Transactional(readOnly = true)
	public List<CitaMedica> listarTodas() {
        return citaMedicaRepository.findAll();
	}

	@Override
    @Transactional(readOnly = true)
	public CitaMedica buscarPorId(Long id) throws EntityNotFoundException {
		if (id == null) {
	        throw new IllegalArgumentException("ID de cita médica no puede ser nulo");
	    }

	    return citaMedicaRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Cita médica no encontrada con ID: " + id));
	}

	@Override
	@Autowired
	public CitaMedica registrarCita(CitaMedicaDTO citaMedicaDTO)
			throws EntityNotFoundException, IllegalOperationException {

	    if (citaMedicaDTO.getFecha() == null) {
	        throw new IllegalOperationException("La fecha de la cita no puede ser nula");
	    }

	    Long pacienteId = citaMedicaDTO.getPacienteId();
	    Paciente paciente = pacienteRepository.findById(pacienteId)
	            .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + pacienteId));

	    CitaMedica nuevaCita = new CitaMedica();
	    nuevaCita.setFecha(citaMedicaDTO.getFecha());
	    nuevaCita.setPaciente(paciente);

	    CitaMedica citaGuardada = citaMedicaRepository.save(nuevaCita);

	    return citaGuardada;
	}
	

	@Override
	public CitaMedica actualizarCita(Long id, CitaMedicaDTO citaMedicaDTO)
			throws EntityNotFoundException, IllegalOperationException {
	    
		CitaMedica citaExistente = citaMedicaRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Cita médica no encontrada"));

	    CitaMedica citaActualizada = citaMedicaRepository.save(citaExistente);

	    return citaActualizada;
	}

	@Override
	public void eliminarCita(Long id) throws EntityNotFoundException, IllegalOperationException {
		 CitaMedica citaMedica = citaMedicaRepository.findById(id)
		            .orElseThrow(() -> new EntityNotFoundException("Cita médica no encontrada"));

		    if (puedeEliminarCita(citaMedica)) {
		        citaMedicaRepository.delete(citaMedica);
		    } else {
		        throw new IllegalOperationException("No se puede eliminar la cita médica debido a ciertas condiciones");
		    }
	}

	private boolean puedeEliminarCita(CitaMedica citaMedica) {
		// Obtiene la fecha actual
	    LocalDateTime ahora = LocalDateTime.now();
	    // Verifica si la fecha de la cita está en el futuro
	    return citaMedica.getFecha().isAfter(ahora);	
	}

	@Override
	public void generarReporteEstadistico() {
		long totalCitas = citaMedicaRepository.count();

	    Map<String, Long> citasPorMes = citaMedicaRepository.countCitasPorMes();

	    System.out.println("Total de citas médicas: " + totalCitas);
	    System.out.println("Citas médicas por mes: " + citasPorMes);		
	}

	@Override
	public List<String> consultarDisponibilidadMedicos() {
		List<Medico> medicos = medicoRepository.findAll();
        List<String> disponibilidadMedicos = new ArrayList<>();

        for (Medico medico : medicos) {
            String estadoDisponibilidad = medico.isDisponible() ? "Disponible" : "No disponible";
            String infoMedico = "Médico ID: " + medico.getId() + ", Nombre: " + medico.getNombre() +
                    ", Disponibilidad: " + estadoDisponibilidad;
            disponibilidadMedicos.add(infoMedico);
        }

        return disponibilidadMedicos;
	}

	@Override
	public void alertasInteraccionesMedicamentosas(Long pacienteId)	throws EntityNotFoundException {
		 Paciente paciente = pacienteRepository.findById(pacienteId)
		            .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

		    String interaccionMedicamentosa = paciente.getInteraccionMedicamentosa();

		    if (interaccionMedicamentosa != null && !interaccionMedicamentosa.isEmpty()) {
		        generarAlertaInteraccionMedicamentosa(interaccionMedicamentosa, paciente);
		    }
	}
	private void generarAlertaInteraccionMedicamentosa(String interaccion, Paciente paciente) {
        
        System.out.println("Alerta de interacción medicamentosa para el paciente: " + paciente.getNombre());
        System.out.println("Detalles de la interacción: " + interaccion);
    }
	
}