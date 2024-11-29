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
    public List<NoticiaBean> buscarNoticias(FiltroBean filtro);
}
