package daos;

import entidades.Comentario;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Paul Alejandro Vazquez Cervantes - 00000241400
 */
public interface IComentarioDAO {
    public Comentario registrarComentario(Comentario comentario) throws PersistenciaException;
    public Comentario buscarComentario(Comentario Comentario) throws PersistenciaException;
    public boolean eliminarComentario(Comentario comentario) throws PersistenciaException;
    public boolean anclarComentario(Comentario comentario) throws PersistenciaException;
    public boolean desanclarComentario(Comentario comentario) throws PersistenciaException;
    public boolean editarComentario(Comentario comentario) throws PersistenciaException;
    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia) throws PersistenciaException;
    public List<Comentario> obtenerComentariosDestacadosPorNoticia(Noticia noticia) throws PersistenciaException;
    public void registrarComentario(Comentario comentario) throws PersistenciaException;
    public Comentario agregarComentarioPublicacion(Comentario comentario) throws PersistenciaException;
    public List<Comentario> obtenerComentarios(List<String> ids) throws PersistenciaException;
}
