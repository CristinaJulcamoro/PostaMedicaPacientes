/**
 * @file: Diagnóstico.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:02:26
 */
package edu.unc.PostaMedicaPacientes.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * 
 */
@Entity
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cita_medica_id", unique = true, nullable = false)
    private CitaMedica citaMedica;

    @OneToMany(mappedBy = "diagnostico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroDeSintomas> sintomas;

    @OneToMany(mappedBy = "diagnostico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diagnostico> tratamientos;

	/**
	 * @param citaMedica2
	 */
    public void setCitaMedica(CitaMedica citaMedica) {
        this.citaMedica = citaMedica;
    }

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public List<Diagnostico> getRecetasMedicas() {
		return tratamientos;
	}
}
