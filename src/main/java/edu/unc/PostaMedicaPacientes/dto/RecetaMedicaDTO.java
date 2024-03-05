/**
 * @file: RecetaMedicaDTO.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:08:44
 */
package edu.unc.PostaMedicaPacientes.dto;

import lombok.Data;

/**
 * 
 */
@Data
public class RecetaMedicaDTO {
    private Long id;
    private String nombreMedico;
    private String fechaPrescripcion;
}
