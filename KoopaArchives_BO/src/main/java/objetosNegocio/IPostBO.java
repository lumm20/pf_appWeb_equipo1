package objetosNegocio;

import entidades_beans.ComentarioBean;
import entidades_beans.PostBean;
import java.util.List;

/**
 *
 * @author luisa M
 */
public interface IPostBO {
    public PostBean subirPublicacion(PostBean post);
    public boolean eliminarPublicacion(PostBean post);
    public PostBean buscarPublicacion(PostBean post);
    public List<PostBean> buscarPublicaciones();
    public boolean actualizarPublicacion(PostBean post, int tipoActualizacion);
    public boolean actualizarReacciones(PostBean post);
    public boolean actualizarComentarios(PostBean post, ComentarioBean comentario, int tipoActualizacion);
}
