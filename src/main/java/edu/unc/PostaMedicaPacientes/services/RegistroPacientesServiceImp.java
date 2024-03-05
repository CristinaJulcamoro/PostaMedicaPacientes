/**
 * @file: RegistroPacientesServiceImp.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:23:54
 */
package edu.unc.PostaMedicaPacientes.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.PostaMedicaPacientes.domain.HistorialMedico;
import edu.unc.PostaMedicaPacientes.domain.Medico;
import edu.unc.PostaMedicaPacientes.domain.Paciente;
import edu.unc.PostaMedicaPacientes.dto.PacienteDTO;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;
import edu.unc.PostaMedicaPacientes.exception.IllegalOperationException;
import edu.unc.PostaMedicaPacientes.repositories.MedicoRepository;
import edu.unc.PostaMedicaPacientes.repositories.PacienteRepository;

/**
 * 
 */
public class RegistroPacientesServiceImp implements RegistroPacientesService{

	@Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Autowired
    private ModelMapper modelMapper;
    
	@Override
	@Transactional(readOnly = true)
    public Paciente buscarPorId(Long id) throws EntityNotFoundException {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + id));
    }

	@Override
	public Paciente registrarPaciente(PacienteDTO pacienteDTO) throws IllegalOperationException {
		if (pacienteDTO.getNombre() == null || pacienteDTO.getNombre().isEmpty()) {
            throw new IllegalOperationException("El nombre del paciente es obligatorio.");
        }
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        return pacienteRepository.save(paciente);
	}

	@Override
	public Paciente actualizarPaciente(Long id, PacienteDTO pacienteDTO)
			throws EntityNotFoundException, IllegalOperationException {
		Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + id));
        if (pacienteDTO.getNombre() == null || pacienteDTO.getNombre().isEmpty()) {
            throw new IllegalOperationException("El nombre del paciente es obligatorio.");
        }
        modelMapper.map(pacienteDTO, paciente);
        return pacienteRepository.save(paciente);
	}

	@Override
	public void eliminarPaciente(Long id) throws EntityNotFoundException, IllegalOperationException {
		Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + id));
        pacienteRepository.delete(paciente);		
	}

	@Override
	public void asignarExpedienteMedico(Long pacienteId) throws EntityNotFoundException, IllegalOperationException {
		Paciente paciente = pacienteRepository.findById(pacienteId)
	            .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + pacienteId));
	    if (paciente.getExpedienteMedico() != null) {
	        throw new IllegalOperationException("El paciente ya tiene un expediente médico asignado.");
	    }
	    String nuevoExpedienteMedico = generarNuevoNumeroExpediente();
	    paciente.setExpedienteMedico(nuevoExpedienteMedico);
	    pacienteRepository.save(paciente);
	}

	private String generarNuevoNumeroExpediente() {
	    return "EXP-" + System.currentTimeMillis(); // Ejemplo simple, puedes cambiar esto		
	}

	@Override
	public void generarReporteEstadistico() {
		List<Paciente> pacientes = pacienteRepository.findAll();
	    Map<String, Integer> pacientesPorMes = new HashMap<>();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
	    for (Paciente paciente : pacientes) {
	        String mes = dateFormat.format(paciente.getFechaNacimiento()); // Asumiendo que la fechaNacimiento es un Date
	        pacientesPorMes.put(mes, pacientesPorMes.getOrDefault(mes, 0) + 1);
	    }
	    System.out.println("Informe Estadístico");
	    System.out.println("-------------------");
	    System.out.println("Total de Pacientes: " + pacientes.size());
	    System.out.println("Total de Pacientes por Mes:");
	    for (Map.Entry<String, Integer> entry : pacientesPorMes.entrySet()) {
	        System.out.println(entry.getKey() + ": " + entry.getValue());
	    }
	    imprimirOtrasEstadisticas(pacientes);
	}
	private void imprimirOtrasEstadisticas(List<Paciente> pacientes) {
	    double sumaEdades = 0;
	    for (Paciente paciente : pacientes) {
	        LocalDate fechaNacimiento = paciente.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
	        sumaEdades += edad;
	    }
	    double edadPromedio = sumaEdades / pacientes.size();
	    System.out.println("Edad Promedio de los Pacientes: " + edadPromedio);
	}	

	@Override
	public List<String> consultarDisponibilidadMedicos() {
		List<Medico> medicosDisponibles = obtenerMedicosDisponibles();
	    return medicosDisponibles.stream()
	            .filter(Medico::isDisponible)
	            .map(Medico::getNombre)
	            .collect(Collectors.toList());
	}

	@Override
	public void alertasInteraccionesMedicamentosas(Long pacienteId) throws EntityNotFoundException {
	    Paciente paciente = pacienteRepository.findById(pacienteId)
	            .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + pacienteId));
	    if (paciente.getHistorialMedico() != null) {
	        HistorialMedico historialMedico = paciente.getHistorialMedico();
	        List<String> medicamentosActuales = obtenerMedicamentosActuales(historialMedico);
	        if (hayInteraccionesMedicamentosas(medicamentosActuales)) {
	            System.out.println("¡Alerta! Interacciones medicamentosas detectadas.");
	        } else {
	            System.out.println("No se encontraron interacciones medicamentosas.");
	        }
	    } else {
	        System.out.println("El paciente no tiene historial médico registrado.");
	    }
	}

	private List<String> obtenerMedicamentosActuales(HistorialMedico historialMedico) {
	    return List.of("Medicamento1", "Medicamento2"); // Cambiar según la lógica real
	}

	private boolean hayInteraccionesMedicamentosas(List<String> medicamentosActuales) {
	    return false; 
	}
	
	private List<Medico> obtenerMedicosDisponibles() {
	     return MedicoRepository.findByDisponible(true);
	    }

}
