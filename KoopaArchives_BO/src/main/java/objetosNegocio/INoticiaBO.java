package objetosNegocio;

import entidades_beans.FiltroBean;
import entidades_beans.NoticiaBean;
import java.util.List;

/**
 *
 * @author karim
 */
public interface INoticiaBO {
    public NoticiaBean publicarNoticia(NoticiaBean post);
    public void actualizarNoticia(NoticiaBean noticia);
    public NoticiaBean buscarNoticia(NoticiaBean noticiaBuscar);
    public boolean eliminarNoticia(NoticiaBean beanEliminar);
    public List<NoticiaBean> buscarNoticias(FiltroBean filtro);
    public boolean anclar(NoticiaBean bean);
    public boolean desanclar(NoticiaBean bean);
    public List<NoticiaBean> buscarNoticias();
}
