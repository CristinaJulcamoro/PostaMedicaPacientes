/**
 * @file: PacienteRepository.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:14:45
 */
package edu.unc.PostaMedicaPacientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.Paciente;

/**
 * 
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByPacienteId(Long pacienteId);

}
