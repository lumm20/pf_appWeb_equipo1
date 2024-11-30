package objetosNegocio;

import entidades.Comentario;
import entidades.Image;
import entidades.Publicacion;
import entidades.Usuario;
import entidades_beans.ComentarioBean;
import entidades_beans.ImagenBean;
import entidades_beans.PostBean;
import entidades_beans.UsuarioBean;
import excepciones.PersistenciaException;
import fachadas.FacadeComentario;
import fachadas.FacadePost;
import fachadas.FacadeUsuario;
import fachadas.IFacadeComentario;
import fachadas.IFacadePost;
import fachadas.IFacadeUsuario;
import factories.FactoryPost;
import factories.FactoryUser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.ConversorImagen;

/**
 *
 * @author luisa M
 */
public class PostBO implements IPostBO {
    private IFacadePost facadePost;
    private IFacadeUsuario facadeUsuario;
    private IFacadeComentario facadeComentario;
    public static final int REMOVER_COMENTARIO = 1;
    public static final int AGREGAR_COMENTARIO = 2;
    public static final int ACTUALIZAR_CONTENIDO = 3;
    public static final int ACTUALIZAR_IMAGEN = 4;
    public static final int ACTUALIZAR_AMBOS = 5;
    
    public PostBO(){
        this.facadePost = new FacadePost();
        this.facadeUsuario = new FacadeUsuario();
        this.facadeComentario = new FacadeComentario();
    }
    
    @Override
    public boolean actualizarPublicacion(PostBean bean, int tipoActualizacion){
        Publicacion publicacion = new Publicacion();
        boolean flag=true;
        switch (tipoActualizacion) {
            case ACTUALIZAR_CONTENIDO->publicacion.setContenido(bean.getTexto());
            case ACTUALIZAR_IMAGEN->{
                Image imagen = ConversorImagen.convertirAImagenDAO(bean.getImagen());
                publicacion.setImagen(imagen);
            }
            case ACTUALIZAR_AMBOS->{
                publicacion.setContenido(bean.getTexto());
                Image imagen = ConversorImagen.convertirAImagenDAO(bean.getImagen());
                publicacion.setImagen(imagen);
            }//si eligen una opcion invalida
            default ->flag = false;
        }
        //solo se actualiza si tipoActualizacion fue una opcion valida
        if(flag){
            //devuelve el resultado de la actualizacion
            return facadePost.actualizarPublicacion(publicacion);
        }
        return flag;
    }
    
    @Override
    public boolean actualizarReacciones(PostBean post){
        Publicacion publicacion = new Publicacion();
        publicacion.setCodigo(post.getCodigo());
        publicacion.setCantidadLikes(post.getLikes());
        return facadePost.actualizarInteraccionesPublicacion(publicacion);
    }

    private boolean agregarComentario(Publicacion publicacion, ComentarioBean bean){
        Comentario comentario = new Comentario();
        comentario.setContenido(bean.getContenido());
        comentario.setIdUsuario(bean.getAutor());
        comentario = facadeComentario.registrarComentarioPublicacion(comentario);
        if (comentario != null) {
            return facadePost.actualizarComentariosPublicacion(publicacion, comentario, FacadePost.NUEVO_COMENTARIO);
        }
        return false;
    }
    
    private boolean removerComentario(Publicacion publicacion, ComentarioBean bean){
        Comentario comentario = new Comentario();
        comentario.setIdComentario(bean.getIdComentario());
        if (facadeComentario.eliminarComentario(comentario)) {
            return facadePost.actualizarComentariosPublicacion(publicacion, comentario, FacadePost.QUITAR_COMENTARIO);
        }return false;
    }
    
    @Override
    public boolean actualizarComentarios(PostBean post, ComentarioBean comentarioBean, int tipoActualizacion){
        Publicacion publicacion = new Publicacion();
        publicacion.setCodigo(post.getCodigo());
        if(tipoActualizacion == AGREGAR_COMENTARIO){
            return agregarComentario(publicacion, comentarioBean);
        }else if(tipoActualizacion == REMOVER_COMENTARIO){
            return removerComentario(publicacion, comentarioBean);
        }
        return false;
    }

    @Override
    public boolean subirPublicacion(PostBean post) {
        try {
            facadePost.registrarPublicacion(convertirBeanPublicacion(post));
            return true;
        } catch (PersistenciaException e) {
            Logger.getLogger(PostBO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }


    @Override
    public boolean eliminarPublicacion(PostBean post) {
        Publicacion publicacion = new Publicacion();
        publicacion.setCodigo(post.getCodigo());
        return facadePost.eliminarPublicacion(publicacion);
    }


    private UsuarioBean buscarPublicador(String username){
        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario = facadeUsuario.buscarUsuario(usuario);
            return convertirUsuario(usuario);
        } catch (PersistenciaException e) {
            Logger.getLogger(PostBO.class.getName()).log(Level.SEVERE, e.getLocalizedMessage());
            return null;
        }
    }
    
    private PostBean setInfoPost(Publicacion publicacion){
        PostBean postEncontrado = convertirPublicacion(publicacion);
        postEncontrado.setLikes(publicacion.getCantidadLikes());
        postEncontrado.setAutor(buscarPublicador(publicacion.getUsernamePublicador()));
        if(publicacion.getComentarios() != null && !publicacion.getComentarios().isEmpty()){
            List<ComentarioBean> comentarios = buscarComentarios(publicacion);
            if (comentarios != null)
                postEncontrado.setComentarios(comentarios);
            else
                postEncontrado.setComentarios(new ArrayList<>());
        }else
            postEncontrado.setComentarios(new ArrayList<>());
        return postEncontrado;
    }
    
    @Override
    public PostBean buscarPublicacion(PostBean post) {
        Publicacion publicacion = new Publicacion();
        publicacion.setCodigo(post.getCodigo());
        publicacion = facadePost.buscarPublicacion(publicacion);
        if(publicacion != null){
            return setInfoPost(publicacion);
        }return null;
    }

    @Override
    public List<PostBean> buscarPublicaciones() {
        List<Publicacion> publicaciones = facadePost.buscarPublicaciones();
        if(publicaciones != null){
            List<PostBean> posts = new ArrayList<>();
            PostBean postBean;
            for (Publicacion publicacionEnc : publicaciones) {
                postBean = convertirPublicacion(publicacionEnc);
                postBean.setAutor(buscarPublicador(publicacionEnc.getUsernamePublicador()));
                posts.add(postBean);
            }
            return posts;
        }
        return null;
    }

    private PostBean convertirPublicacion(Publicacion publicacion ){
        PostBean bean = new PostBean();
        bean.setCategoria(publicacion.getCategoria());
        
        if(publicacion.getImagen() != null){
            ImagenBean imagen = ConversorImagen.convertirAImagenBean2(publicacion.getImagen());
            bean.setImagen(imagen);
        }
        bean.setTexto(publicacion.getContenido());
        bean.setFechaCreacion(publicacion.getFechaCreacion());
        bean.setCodigo(publicacion.getCodigo());
        
        return bean;
    }

    private List<ComentarioBean> buscarComentarios(Publicacion post){
        List<Comentario> comentarios = facadeComentario.obtenerComentarios(post.getComentarios());
        if(comentarios != null){
            List<ComentarioBean> beans = new ArrayList<>();
            ComentarioBean bean;
            for (Comentario comentario : comentarios) {
                bean = convertirComentario(comentario);
                bean.setIdPost(post.getCodigo());
                beans.add(bean);
            }
            
            return beans;
        }
        return null;
    }
    
    private ComentarioBean convertirComentario(Comentario comentario){
        ComentarioBean bean = new ComentarioBean();
        bean.setAutor(comentario.getIdUsuario());
        Image imagen = comentario.getUsuario().getImagen();
        bean.setImagenAutor(ConversorImagen.convertirAImagenBean2(imagen));
        bean.setContenido(comentario.getContenido());
        bean.setFechaCreacion(comentario.getFechaPublicacion());
        bean.setIdComentario(comentario.getIdComentario());
        return bean;
    }
    
    private Publicacion convertirBeanPublicacion(PostBean bean){
        Publicacion publicacion = (Publicacion)FactoryPost.crearPost(FactoryPost.PUBLICACION);
        if(bean.getCategoria() != null)
            publicacion.setCategoria(bean.getCategoria());
        if(bean.getUltimaModificacion() != null)
            publicacion.setUltimaModificacion(bean.getUltimaModificacion());
        if(bean.getCodigo() != null)
            publicacion.setCodigo(bean.getCodigo());
        if(bean.getAutor()!= null){
            //String username = obtenerUsernamePublicador(bean.getPublicador());
            publicacion.setUsernamePublicador(bean.getAutor().getUsername());
        }
        publicacion.setImagen(ConversorImagen.convertirAImagenDAO(bean.getImagen()));
        publicacion.setFechaCreacion(bean.getFechaCreacion());
//        publicacion.setContenido(bean.getContenido().getDescripcion());
        return publicacion;
    }
    
    private String obtenerUsernamePublicador(UsuarioBean bean){
        Usuario user = convertirBeanUsuario(bean);
        try {
            user = facadeUsuario.buscarUsuario(user);
            String username = user.getUsername();
            return username;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PostBO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private UsuarioBean convertirUsuario(Usuario usuario){
        UsuarioBean bean = new UsuarioBean();
        bean.setImagen(ConversorImagen.convertirAImagenBean2(usuario.getImagen()));
        bean.setUsername(usuario.getUsername());
        return bean;
    }
    
    private Usuario convertirBeanUsuario(UsuarioBean bean) {
        Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
        usuario.setUsername(bean.getUsername());
        return usuario;
    }
}
