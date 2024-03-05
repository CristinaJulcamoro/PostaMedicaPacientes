/**
 * @file: RegistroDeAlergiasCondicionesMedicasRepository.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:18:09
 */
package edu.unc.PostaMedicaPacientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.RegistroDeAlergiasCondicionesMedicas;

/**
 * 
 */
@Repository
public interface RegistroDeAlergiasCondicionesMedicasRepository extends JpaRepository<RegistroDeAlergiasCondicionesMedicas, Long> {
    List<RegistroDeAlergiasCondicionesMedicas> findByPacienteId(Long pacienteId);

}
