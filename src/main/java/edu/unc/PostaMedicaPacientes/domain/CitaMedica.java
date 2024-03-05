/**
 * @file: CitaMédica.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:02:00
 */
package edu.unc.PostaMedicaPacientes.domain;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 * 
 */
@Entity
public class CitaMedica {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    private LocalDateTime fecha;
    
    @OneToOne(mappedBy = "citaMedica", cascade = CascadeType.ALL, orphanRemoval = true)
    private Diagnostico diagnostico;

	/**
	 * @param fecha2
	 */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

	/**
	 * @param paciente2
	 */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
        if (diagnostico != null) {
            diagnostico.setCitaMedica(this);
        }
    }

	/**
	 * @return
	 */
	public LocalDateTime getFecha() {
        return this.fecha;
	}

	
}
