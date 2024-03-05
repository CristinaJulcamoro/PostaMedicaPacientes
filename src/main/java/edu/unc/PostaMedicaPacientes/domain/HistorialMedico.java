/**
 * @file: HistorialMédico.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:04:08
 */
package edu.unc.PostaMedicaPacientes.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * 
 */

@Entity
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "historialMedico", cascade = CascadeType.ALL)
    private Paciente paciente;

    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroDeSintomas> registrosSintomas;

    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroDeAlergiasCondicionesMedicas> registrosAlergiasCondicionesMedicas;

    @OneToOne(mappedBy = "paciente")
    private HistorialMedico historialMedico;
    
	private String interaccionMedicamentosa;

	/**
	 * @param interaccionMedicamentosa
	 */
	public void setInteraccionMedicamentosa(String interaccionMedicamentosa) {
	    this.interaccionMedicamentosa = interaccionMedicamentosa;		
	}

	/**
	 * @return
	 */
	public Object getExpedienteMedico() {
		StringBuilder expedienteMedico = new StringBuilder();
		if (registrosSintomas != null && !registrosSintomas.isEmpty()) {
			expedienteMedico.append("Registros de Síntomas:\n");
			for (RegistroDeSintomas registro : registrosSintomas) {
				expedienteMedico.append(registro.getDescripcion()).append("\n");
			}
		}
		if (registrosAlergiasCondicionesMedicas != null && !registrosAlergiasCondicionesMedicas.isEmpty()) {
			expedienteMedico.append("\nRegistros de Alergias y Condiciones Médicas:\n");
			for (RegistroDeAlergiasCondicionesMedicas registro : registrosAlergiasCondicionesMedicas) {
				expedienteMedico.append(registro.getDescripcion()).append("\n");
			}
		}
		return expedienteMedico.toString();
	}

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
	public List<Medicamento> getRecetasMedicas() {
		List<Medicamento> recetasMedicas = new ArrayList<>();
        if (historialMedico != null && historialMedico.getRecetasMedicas() != null) {
            recetasMedicas.addAll(historialMedico.getRecetasMedicas());
        }
        return recetasMedicas;
	}

}
