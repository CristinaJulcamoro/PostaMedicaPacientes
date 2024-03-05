/**
 * @file: Paciente.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:01:38
 */
package edu.unc.PostaMedicaPacientes.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String fechaNacimiento;
    
    private String interaccionMedicamentosa;

    private String contacto;

    @OneToOne(mappedBy = "paciente")
    private HistorialMedico historialMedico;

	/**
	 * @return
	 */
	public Long getId() {
        return id;

	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public void setInteraccionMedicamentosa(String interaccionMedicamentosa) {
		if (historialMedico != null) {
			historialMedico.setInteraccionMedicamentosa(interaccionMedicamentosa);
		}
	}
	public String getInteraccionMedicamentosa() {
	    return this.interaccionMedicamentosa;
	}

	/**
	 * @return
	 */
	public Object getExpedienteMedico() {
		if (historialMedico != null) {
			return historialMedico.getExpedienteMedico();
		} else {
			return null;
		}	}

	/**
	 * @param nuevoExpedienteMedico
	 */
	public void setExpedienteMedico(String nuevoExpedienteMedico) {
		if (historialMedico != null) {
            historialMedico.setExpedienteMedico(nuevoExpedienteMedico);
        }	
		}

	/**
	 * @return
	 */
	public Date getFechaNacimiento() {
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        return sdf.parse(fechaNacimiento);
	    } catch (ParseException e) {
	        e.printStackTrace(); // Manejo de errores, puedes cambiar esto según tus necesidades
	        return null;	}
	}

	/**
	 * @return
	 */
	public HistorialMedico getHistorialMedico() {
		return historialMedico;
	}

}
