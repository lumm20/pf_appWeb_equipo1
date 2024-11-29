package objetosNegocio;

import entidades.FiltroNoticia;
import entidades.Image;
import entidades.Noticia;
import entidades_beans.FiltroBean;
import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;
import entidades_beans.UsuarioBean;
import excepciones.PersistenciaException;
import fachadas.FacadePost;
import fachadas.IFacadePost;
import factories.FactoryPost;
import java.util.ArrayList;
import java.util.List;
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
    public void actualizarNoticia(NoticiaBean bean) {
        Noticia noticia = convertirANoticiaDAO(bean);
        facadeNoticias.actualizarNoticia(noticia);
    }

    @Override
    public NoticiaBean buscarNoticia(NoticiaBean noticiaBuscar) {
        Noticia noticia = convertiANoticiaDAOSoloCodigo(noticiaBuscar);

        Noticia noticiaConsultada = facadeNoticias.buscarNoticia(noticia);

        if (noticiaConsultada == null) {
            return null;
        }

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

    private FiltroNoticia convertirFiltro(FiltroBean bean) {
        FiltroNoticia filtro = new FiltroNoticia();

        filtro.setCategoria(bean.getCategoria());
        filtro.setTitulo(bean.getTitulo());
        filtro.setDestacada(bean.isDestacada());
        filtro.setInicio(bean.isInicio());
        filtro.setFechaDesde(bean.getFechaDesde());
        filtro.setFechaHasta(bean.getFechaHasta());

        return filtro;
    }

    private Noticia convertiANoticiaDAOSoloCodigo(NoticiaBean bean) {
        Noticia noticia = new Noticia(bean.getCodigo());
        return noticia;
    }

    @Override
    public List<NoticiaBean> buscarNoticias(FiltroBean filtro) {
        List<NoticiaBean> beans = new ArrayList<>();

        try {
            List<Noticia> noticias = facadeNoticias.buscarNoticias(convertirFiltro(filtro));
            for (Noticia noticia : noticias) {
                NoticiaBean bean = convertirBeanNoticia(noticia);
                beans.add(bean);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(NoticiaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beans;

    }

}
