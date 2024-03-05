/**
 * @file: RecetaMédica.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:03:33
 */
package edu.unc.PostaMedicaPacientes.domain;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
public class RecetaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Farmacia farmacia;

    @ManyToOne
    private Paciente paciente;
}
