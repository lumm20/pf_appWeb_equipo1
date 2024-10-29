package daos;

import entidades.Comentario;
import excepciones.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Paul Alejandro Vazquez Cervantes - 00000241400
 */
public interface IComentarioDAO {
    public void publicarComentario(Comentario comentario, ObjectId idNoticia) throws PersistenciaException;
    public Comentario buscarComentario(ObjectId idComentario) throws PersistenciaException;
    public boolean eliminarComentarioPorId(ObjectId idComentario) throws PersistenciaException;
    public void anclarComentario(ObjectId idComentario) throws PersistenciaException;
    public void editarComentario(ObjectId idComentario, String nuevoComentario) throws PersistenciaException;
    public List<Comentario> obtenerComentariosPorNoticia(ObjectId idNoticia) throws PersistenciaException;
}
