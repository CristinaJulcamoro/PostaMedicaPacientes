/**
 * @file: FarmaciaDTO.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:08:28
 */
package edu.unc.PostaMedicaPacientes.dto;

import java.util.List;

import edu.unc.PostaMedicaPacientes.domain.RecetaMedica;
import lombok.Data;

/**
 * 
 */
@Data
public class FarmaciaDTO {
    private Long id;
    // Otros atributos según sea necesario
    private List<RecetaMedica> recetasMedicas;
}
