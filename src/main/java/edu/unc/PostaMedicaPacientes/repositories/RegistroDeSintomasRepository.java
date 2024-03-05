/**
 * @file: RegistroDeSintomasRepository.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:17:49
 */
package edu.unc.PostaMedicaPacientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.RegistroDeSintomas;

/**
 * 
 */

@Repository
public interface RegistroDeSintomasRepository extends JpaRepository<RegistroDeSintomas, Long> {
    List<RegistroDeSintomas> findByPacienteId(Long pacienteId);

}
