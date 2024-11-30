package daos;

import entidades.Comentario;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Paul Alejandro Vazquez Cervantes - 00000241400
 */
public interface IComentarioDAO {
    public void registrarComentario(Comentario comentario) throws PersistenciaException;
    public Comentario agregarComentarioPublicacion(Comentario comentario) throws PersistenciaException;
    public Comentario buscarComentario(Comentario Comentario) throws PersistenciaException;
    public boolean eliminarComentario(Comentario comentario) throws PersistenciaException;
    public void anclarComentario(Comentario comentario) throws PersistenciaException;
    public void editarComentario(Comentario comentario) throws PersistenciaException;
    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia) throws PersistenciaException;
    public List<Comentario> obtenerComentarios(List<String> ids) throws PersistenciaException;
}
