/**
 * @file: MedicoRepository.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 02:05:54
 */
package edu.unc.PostaMedicaPacientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.Medico;

/**
 * 
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByPacienteId(Long pacienteId);

	/**
	 * @param b
	 * @return
	 */
	static List<Medico> findByDisponible(boolean b) {
	    return MedicoRepository.findByDisponible(true);
	}

}
