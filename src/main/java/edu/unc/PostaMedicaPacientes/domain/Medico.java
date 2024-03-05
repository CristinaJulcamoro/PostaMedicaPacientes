/**
 * @file: Medico.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:42:11
 */
package edu.unc.PostaMedicaPacientes.domain;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String especialidad;
    
    private boolean disponible;


    @OneToOne(mappedBy = "medico")
    private CitaMedica citaMedica;


	/**
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}


	/**
	 * @return
	 */
	public long getId() {
		return id;
	}


	/**
	 * @return
	 */
	public boolean isDisponible() {
		return disponible;
	}
	 public void setDisponible(boolean disponible) {
	        this.disponible = disponible;
	    }
}
