package daos;

import entidades.Comentario;
import excepciones.PersistenciaException;
import org.bson.types.ObjectId;

/**
 *
 * @author Paul Alejandro Vazquez Cervantes - 00000241400
 */
public interface IComentarioDAO {
    public void publicarComentario(Comentario comentario, ObjectId idNoticia) throws PersistenciaException;
}
