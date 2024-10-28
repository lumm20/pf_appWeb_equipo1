package excepciones;


/**
 *
 * @author José Karim Franco Valencia - 245138
 */
public class PersistenciaException extends Exception{
     /**
     * Constructor vacío.
     */
    public PersistenciaException() {
    }

    /**
     * Constructor que establece el mensaje de la excepción.
     * @param message de la excepción.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Constructor que establece el mensaje y la causa de la excepción.
     * @param message de la excepción.
     * @param cause de la excepción.
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que establece la causa de la excepción.
     * @param cause de la excepción
     */
    public PersistenciaException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructor que establece el mensaje, la causa, y otros atributos de la excepción.
     * 
     * @param message El mensaje descriptivo de la excepción.
     * @param cause La causa de la excepción.
     * @param enableSuppression Indica si la supresión de excepciones está habilitada o no.
     * @param writableStackTrace Indica si se debe escribir el stack trace en la salida estándar de error.
     */
    public PersistenciaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

