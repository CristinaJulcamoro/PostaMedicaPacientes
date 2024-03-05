/**
 * @file: RegistroDeSíntomas.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:04:58
 */
package edu.unc.PostaMedicaPacientes.domain;

import java.util.Date;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
public class RegistroDeSintomas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    
    private String sintomas;

    @ManyToOne
    private Paciente paciente;

	/**
	 * @return
	 */
	public Object getDescripcion() {
		return "Fecha: " + fecha + "\nSíntomas: " + sintomas;
	}
}
