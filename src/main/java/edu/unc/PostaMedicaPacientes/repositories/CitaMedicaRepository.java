/**
 * @file: CitaMedicaRepository.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:15:05
 */
package edu.unc.PostaMedicaPacientes.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.Paciente;

/**
 * 
 */
@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
    List<CitaMedica> findByPacienteId(Long pacienteId);
	//List<CitaMedica> findByPaciente(Paciente paciente);

	/**
	 * @return
	 */
	Map<String, Long> countCitasPorMes();

}
