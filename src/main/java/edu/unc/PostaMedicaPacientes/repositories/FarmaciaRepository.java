/**
 * @file: FarmaciaRepository.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:16:37
 */
package edu.unc.PostaMedicaPacientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.Farmacia;

/**
 * 
 */
@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    List<Farmacia> findByPacienteId(Long pacienteId);

}
