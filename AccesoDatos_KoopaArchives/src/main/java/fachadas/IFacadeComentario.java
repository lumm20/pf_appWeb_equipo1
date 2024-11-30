package fachadas;

import entidades.Comentario;
import entidades.Noticia;
import java.util.List;

/**
 *
 * @author karim
 */
public interface IFacadeComentario {

    public void registrarComentario(Comentario comentario);
    public Comentario registrarComentarioPublicacion(Comentario comentario);

    public Comentario buscarComentario(Comentario Comentario);

    public boolean eliminarComentario(Comentario comentario);

    public void anclarComentario(Comentario comentario);

    public void editarComentario(Comentario comentario);

    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia);

    public List<Comentario> obtenerComentarios(List<String> ids);
}
