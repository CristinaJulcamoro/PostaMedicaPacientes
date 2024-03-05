/**
 * @file: DiagnosticoDTO.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:08:00
 */
package edu.unc.PostaMedicaPacientes.dto;

import edu.unc.PostaMedicaPacientes.domain.CitaMedica;
import lombok.Data;

/**
 * 
 */
@Data
public class DiagnosticoDTO {
    private Long id;
    private String descripcion;
    private CitaMedica citaMedica;
}
