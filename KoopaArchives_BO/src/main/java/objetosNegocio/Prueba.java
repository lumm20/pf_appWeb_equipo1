package objetosNegocio;

import daos.NoticiaDAO;
import entidades.Noticia;
import entidades_beans.FiltroBean;
import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;

/**
 *
 * @author karim
 */
public class Prueba {
    public static void main(String[] args) {
        NoticiaBO bo = new NoticiaBO();
        NoticiaBean bean = new NoticiaBean();
        bean.setCodigo("N6077345630");
        NoticiaBean noticiaCambiar  = bo.buscarNoticia(bean);
        System.out.println(noticiaCambiar);
        
        noticiaCambiar.setDestacada(false);
        noticiaCambiar.setTexto("titulo cambiado tirisisisi");
        bo.actualizarNoticia(noticiaCambiar);
//        noticiaCambiar.setDestacada(false);
//        dao.actualizarNoticia(noticiaCambiar);
        System.out.println(bo.buscarNoticia(bean));
    }
}
