/**
 * @file: Medicamento.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:03:47
 */
package edu.unc.PostaMedicaPacientes.domain;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

	/**
	 * @param nombre2
	 */
	public void setNombre(String nombre2) {
        this.nombre = nombre2;
	}

	/**
	 * @param descripcion2
	 */
	public void setDescripcion(String descripcion2) {
        this.descripcion = descripcion;
		
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
}
