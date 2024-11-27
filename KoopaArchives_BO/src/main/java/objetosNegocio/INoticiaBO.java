package objetosNegocio;

import entidades_beans.NoticiaBean;

/**
 *
 * @author karim
 */
public interface INoticiaBO {
    public NoticiaBean publicarNoticia(NoticiaBean post);
    
    public NoticiaBean buscarNoticia(NoticiaBean noticiaBuscar);
}
