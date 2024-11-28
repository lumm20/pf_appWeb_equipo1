package objetosNegocio;

import entidades.Image;
import entidades.Noticia;
import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;
import entidades_beans.UsuarioBean;
import excepciones.PersistenciaException;
import fachadas.FacadePost;
import fachadas.IFacadePost;
import factories.FactoryPost;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.ConversorImagen;

/**
 *
 * @author karim
 */
public class NoticiaBO implements INoticiaBO {

    private final IFacadePost facadeNoticias;

    public NoticiaBO() {
        this.facadeNoticias = new FacadePost();
    }

    @Override
    public NoticiaBean publicarNoticia(NoticiaBean post) {
        Noticia noticia = convertirANoticiaDAO(post);
        try {
            noticia = facadeNoticias.registrarNoticia(noticia);
            
            return convertirBeanNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(NoticiaBO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public NoticiaBean buscarNoticia(NoticiaBean noticiaBuscar) {
        Noticia noticia = convertiANoticiaDAOSoloCodigo(noticiaBuscar);

        Noticia noticiaConsultada = facadeNoticias.buscarNoticia(noticia);
        
        if(noticiaConsultada == null) return null;
        
        NoticiaBean noticiaEncontrada = convertirBeanNoticia(noticiaConsultada);
        return noticiaEncontrada;
    }

    private Noticia convertirANoticiaDAO(NoticiaBean bean) {
        Noticia noticia = (Noticia) FactoryPost.crearPost(FactoryPost.NOTICIA);
        noticia.setTitulo(bean.getTitulo());
        noticia.setCategoria(bean.getCategoria());
        noticia.setFechaCreacion(bean.getFechaCreacion());
        if (bean.getCodigo() != null) {
            noticia.setCodigo(bean.getCodigo());
        }
        noticia.setDestacada(bean.isDestacada());
        noticia.setContenido(bean.getTexto());
        noticia.setAutor(bean.getAutor().getUsername());
        Image imagen = ConversorImagen.convertirAImagenDAO(bean.getImagen());
        noticia.setImagen(imagen);

        return noticia;
    }

    private NoticiaBean convertirBeanNoticia(Noticia noticia) {
        NoticiaBean bean = new NoticiaBean();
        bean.setTitulo(noticia.getTitulo());
        bean.setDestacada(noticia.isDestacada());
        bean.setCategoria(noticia.getCategoria());
        bean.setCodigo(noticia.getCodigo());
        bean.setTexto(noticia.getContenido());
        bean.setFechaCreacion(noticia.getFechaCreacion());
        UsuarioBean usuario = new UsuarioBean();
        usuario.setUsername(noticia.getAutor());
        bean.setAutor(usuario);
        ImagenBean imagen = ConversorImagen.convertirAImagenBean2(noticia.getImagen());
        bean.setImagen(imagen);

        return bean;
    }

    private Noticia convertiANoticiaDAOSoloCodigo(NoticiaBean bean) {
        Noticia noticia = new Noticia(bean.getCodigo());
        return noticia;
    }
}
