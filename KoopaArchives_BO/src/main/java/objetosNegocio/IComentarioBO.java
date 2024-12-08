package objetosNegocio;

import entidades_beans.ComentarioBean;
import entidades_beans.NoticiaBean;
import java.util.List;

/**
 *
 * @author karim
 */
public interface IComentarioBO {
    public ComentarioBean registrarComentario(ComentarioBean comentario);
    public boolean editarComentario(ComentarioBean comentario);
    public boolean borrarComentario(ComentarioBean comentario);
    public boolean anclarComentario(ComentarioBean comentario);
    public boolean desanclarComentario(ComentarioBean comentario);
    public List<ComentarioBean> obtenerComentariosPorNoticia(NoticiaBean noticia);
    public List<ComentarioBean> obtenerComentariosDestacadosPorNoticia(NoticiaBean noticia);
}
