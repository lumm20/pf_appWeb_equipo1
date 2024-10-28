package daos;

import excepciones.PersistenciaException;

/**
 *
 * @author Paul Alejandro Vazquez Cervantes - 00000241400
 */
public interface IComentarioDAO {
    public void publicarComentario() throws PersistenciaException;
}
