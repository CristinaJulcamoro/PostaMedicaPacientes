/**
 * @file: CitaMedicaDTO.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:05:59
 */
package edu.unc.PostaMedicaPacientes.dto;

import java.time.LocalDateTime;
import java.util.Date;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import edu.unc.PostaMedicaPacientes.domain.Medico;
import edu.unc.PostaMedicaPacientes.domain.Paciente;
import lombok.Data;

/**
 * 
 */
@Data
public class CitaMedicaDTO {
    private Long id;
    private LocalDateTime fecha;
    private String descripcion;
    private Medico medico;
    private Paciente paciente;
	/**
	 * @return
	 */
    public LocalDateTime getFecha() {
        return fecha;
    }
	/**
	 * @return
	 */
    public Long getPacienteId() {
        if (paciente != null) {
            return paciente.getId();
        }
        return null;
    }
	
	
}
