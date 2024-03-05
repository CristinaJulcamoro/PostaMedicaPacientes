/**
 * @file: RegistroDeAlergiasCondicionesMédicas.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:05:11
 */
package edu.unc.PostaMedicaPacientes.domain;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
public class RegistroDeAlergiasCondicionesMedicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

	/**
	 * @return
	 */
	public Object getDescripcion() {
		return "Registro de Alergias y Condiciones Médicas para el paciente " + paciente.getNombre();
	}

}
