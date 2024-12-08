package fachadas;

import entidades.Comentario;
import entidades.Noticia;
import java.util.List;

/**
 *
 * @author karim
 */
public interface IFacadeComentario {
    public Comentario registrarComentario(Comentario comentario);
    public Comentario buscarComentario(Comentario Comentario);
    public boolean eliminarComentario(Comentario comentario);
    public boolean anclarComentario(Comentario comentario);
    public boolean desanclarComentario(Comentario comentario);
    public boolean editarComentario(Comentario comentario);
    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia);
    public List<Comentario> obtenerComentariosDestacadosPorNoticia(Noticia noticia);
}
