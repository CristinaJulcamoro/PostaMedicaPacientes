/**
 * @file: EntityNotFoundException.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:12:36
 */
package edu.unc.PostaMedicaPacientes.exception;

/**
 * Excepcion que se lanza cuando el proceso de búsqueda no encuentra una entidad.
 */
public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
    
}
