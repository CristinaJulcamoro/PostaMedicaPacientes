/**
 * @file: ErrorMessage.java
 * @author: (c)2024 jvale_uznxw
 * @created: 3 mar. 2024 08:33:28
 */
package edu.unc.PostaMedicaPacientes.exception;

/**
 * 
 */
public final class ErrorMessage {
    public static final String CITAMEDICA_NOT_FOUND = "La cita médica con el ID proporcionado no fue encontrada";
    public static final String OTHER_ERROR_MESSAGE = "Otro mensaje de error personalizado";
    public static final String DIAGNOSTICO_NOT_FOUND = "Diagnóstico no encontrado";


    private ErrorMessage() {
        throw new IllegalStateException("Utility class");
    }
}
