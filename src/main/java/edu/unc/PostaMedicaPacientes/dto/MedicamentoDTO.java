/**
 * @file: MedicamentoDTO.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:09:01
 */
package edu.unc.PostaMedicaPacientes.dto;

import lombok.Data;

/**
 * 
 */
@Data
public class MedicamentoDTO {
    private Long id;
    private String nombre;
	private String descripcion;
	/**
	 * @return
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
