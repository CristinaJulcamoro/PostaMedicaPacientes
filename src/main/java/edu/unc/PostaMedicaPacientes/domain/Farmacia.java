/**
 * @file: Farmacia.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:03:12
 */
package edu.unc.PostaMedicaPacientes.domain;

import java.util.List;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 
 */

@Entity
public class Farmacia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "farmacia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecetaMedica> recetasMedicas;

    @OneToMany(mappedBy = "farmacia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> inventarioMedicamentos;
}
