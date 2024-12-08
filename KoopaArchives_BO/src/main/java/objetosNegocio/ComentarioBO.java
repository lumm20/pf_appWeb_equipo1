package objetosNegocio;

import entidades.Comentario;
import entidades.Noticia;
import entidades_beans.ComentarioBean;
import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;
import fachadas.FacadeComentario;
import fachadas.IFacadeComentario;
import java.util.ArrayList;
import java.util.List;
import utilities.ConversorImagen;

/**
 *
 * @author karim
 */
public class ComentarioBO implements IComentarioBO {

    private IFacadeComentario fachadaComentario;

    public ComentarioBO() {
        fachadaComentario = new FacadeComentario();
    }

    @Override
    public ComentarioBean registrarComentario(ComentarioBean bean) {
        Comentario comentario = convertirEntidad(bean);
        ComentarioBean aux = convertirBean(fachadaComentario.registrarComentario(comentario));
        return aux;
    }

    @Override
    public boolean editarComentario(ComentarioBean bean) {
        Comentario comentario = convertirEntidad(bean);
        return fachadaComentario.editarComentario(comentario);

    }

    @Override
    public boolean borrarComentario(ComentarioBean bean) {
        Comentario comentario = new Comentario();
        comentario.setIdComentario(bean.getIdComentario());
        return fachadaComentario.eliminarComentario(comentario);
    }

    @Override
    public boolean anclarComentario(ComentarioBean bean) {
        Comentario comentario = new Comentario();
        comentario.setIdComentario(bean.getIdComentario());
        return fachadaComentario.anclarComentario(comentario);
    }

    @Override
    public boolean desanclarComentario(ComentarioBean bean) {
        Comentario comentario = new Comentario();
        comentario.setIdComentario(bean.getIdComentario());
        return fachadaComentario.desanclarComentario(comentario);
    }

    @Override
    public List<ComentarioBean> obtenerComentariosPorNoticia(NoticiaBean bean) {
        Noticia noticia = new Noticia(bean.getCodigo());
        List<Comentario> comentarios = fachadaComentario.obtenerComentariosPorNoticia(noticia);

        return convertirBeans(comentarios);
    }

    @Override
    public List<ComentarioBean> obtenerComentariosDestacadosPorNoticia(NoticiaBean bean) {
        Noticia noticia = new Noticia(bean.getCodigo());
        List<Comentario> comentarios = fachadaComentario.obtenerComentariosDestacadosPorNoticia(noticia);

        return convertirBeans(comentarios);
    }

    private List<ComentarioBean> convertirBeans(List<Comentario> comentarios) {
        List<ComentarioBean> beans = new ArrayList<>();
        for (Comentario comentario : comentarios) {
            ComentarioBean bean = convertirBean(comentario);
            beans.add(bean);
        }

        return beans;
    }

    private ComentarioBean convertirBean(Comentario comentario) {
        ComentarioBean bean = new ComentarioBean();
        bean.setAutor(comentario.getUsuario().getUsername());
        bean.setCodigoNoticia(comentario.getCodigoNoticia());
        ImagenBean imagen = ConversorImagen.convertirAImagenBean2(comentario.getUsuario().getImagen());
        bean.setPerfilUsuario(imagen);
        bean.setAnclado(comentario.isAnclado());
        bean.setContenido(comentario.getContenido());
        bean.setIdComentario(comentario.getIdComentario());
        bean.setFechaModificacion(comentario.getFechaModificacion());
        bean.setFechaPublicacion(comentario.getFechaPublicacion());

        return bean;
    }

    private Comentario convertirEntidad(ComentarioBean bean) {
        Comentario comentario = new Comentario();
        comentario.setCodigoNoticia(bean.getCodigoNoticia());
        comentario.setAnclado(bean.getAnclado());
        comentario.setAutor(bean.getAutor());
        comentario.setContenido(bean.getContenido());
        comentario.setIdComentario(bean.getIdComentario());

        return comentario;
    }

}
