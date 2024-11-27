package objetosNegocio;

import entidades_beans.PostBean;

/**
 *
 * @author luisa M
 */
public interface IPostBO {
    public boolean subirPublicacion(PostBean post);
    public boolean eliminarPublicacion(PostBean post);
    public PostBean buscarPublicacion(PostBean post);
}
