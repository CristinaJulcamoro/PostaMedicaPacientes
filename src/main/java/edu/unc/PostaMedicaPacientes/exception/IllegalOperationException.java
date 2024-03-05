/**
 * @file: IllegalOperationException.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 01:12:52
 */
package edu.unc.PostaMedicaPacientes.exception;

/**
 * Excepcion que se lanza cuando se realiza una operación ilegal.
 */
public class IllegalOperationException extends Exception {

    private static final long serialVersionUID = 1L;

    public IllegalOperationException(String message) {
        super(message);
    }
}
