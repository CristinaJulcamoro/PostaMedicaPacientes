/**
 * @file: MedicamentoRepository.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:17:18
 */
package edu.unc.PostaMedicaPacientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.Medicamento;

/**
 * 
 */
@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByPacienteId(Long pacienteId);

	/**
	 * @param nombre
	 * @return
	 */
	boolean existsByNombre(String nombre);

}
