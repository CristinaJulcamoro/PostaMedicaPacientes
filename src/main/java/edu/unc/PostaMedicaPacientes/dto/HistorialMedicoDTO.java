/**
 * @file: HistorialMedicoDTO.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:09:17
 */
package edu.unc.PostaMedicaPacientes.dto;

import java.util.List;

import edu.unc.PostaMedicaPacientes.domain.Diagnostico;
import edu.unc.PostaMedicaPacientes.domain.RegistroDeSintomas;
import lombok.Data;

/**
 * 
 */
@Data
public class HistorialMedicoDTO {
    private Long id;
    // Otros atributos según sea necesario
    private List<Diagnostico> diagnosticos;
    private List<RegistroDeSintomas> registrosDeSintomas;
}
