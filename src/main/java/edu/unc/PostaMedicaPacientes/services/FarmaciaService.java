/**
 * @file: FarmaciaService.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:21:32
 */
package edu.unc.PostaMedicaPacientes.services;

import java.util.List;

import edu.unc.PostaMedicaPacientes.domain.Medicamento;
import edu.unc.PostaMedicaPacientes.dto.MedicamentoDTO;
import edu.unc.PostaMedicaPacientes.exception.EntityNotFoundException;

/**
 * 
 */
public interface FarmaciaService {
	List<Medicamento> listarMedicamentos();

    Medicamento buscarMedicamentoPorId(Long id) throws EntityNotFoundException;

    void registrarMedicamento(MedicamentoDTO medicamentoDTO);

    void actualizarMedicamento(Long id, MedicamentoDTO medicamentoDTO)throws EntityNotFoundException;

    void eliminarMedicamento(Long id)throws EntityNotFoundException;

    List<String> consultarDisponibilidadMedicamentos();

    void alertasInteraccionesMedicamentosas(Long pacienteId)throws EntityNotFoundException;
}
