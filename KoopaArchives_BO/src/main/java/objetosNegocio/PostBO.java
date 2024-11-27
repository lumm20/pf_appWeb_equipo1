package objetosNegocio;

import entidades.Noticia;
import entidades.Publicacion;
import entidades.Usuario;
import entidades_beans.ContenidoBean;
import entidades_beans.FiltroBusquedaBean;
import entidades_beans.ImagenBean;
import entidades_beans.PostBean;
import entidades_beans.SubtemaBean;
import entidades_beans.UsuarioBean;
import excepciones.PersistenciaException;
import fachadas.FacadePost;
import fachadas.FacadeUsuario;
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
    public PostBO(){
        this.facadePost = new FacadePost();
        this.facadeUsuario = new FacadeUsuario();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    @Override
    public PostBean buscarPublicacion(PostBean post) {
        Publicacion publicacion = new Publicacion();
        publicacion.setNumPost(post.getNumPost());
        publicacion = facadePost.buscarPublicacion(publicacion);
        if(publicacion != null){
            PostBean postEncontrado = convertirPublicacion(publicacion);
            postEncontrado.setPublicador(buscarPublicador(publicacion.getUsernamePublicador()));
            return postEncontrado;
        }return null;
    }

    @Override
    public List<PostBean> buscarPublicacionesPorCategoria(PostBean post) {
        Publicacion publicacion = new Publicacion();
        publicacion.setCategoria(post.getCategoria());
        List<Publicacion> publicaciones = facadePost.buscarPublicacionesPorCategoria(publicacion);
        if(publicaciones != null){
            List<PostBean> posts = new ArrayList<>();
            PostBean postBean;
            for (Publicacion publicacionEnc : publicaciones) {
                postBean = convertirPublicacion(publicacionEnc);
                postBean.setPublicador(buscarPublicador(publicacionEnc.getUsernamePublicador()));
                posts.add(postBean);
            }
            return posts;
        }
        return null;
    }
    @Override
    public List<PostBean> buscarPublicaciones() {
        List<Publicacion> publicaciones = facadePost.buscarPublicaciones();
        if(publicaciones != null){
            List<PostBean> posts = new ArrayList<>();
            PostBean postBean;
            for (Publicacion publicacionEnc : publicaciones) {
                postBean = convertirPublicacion(publicacionEnc);
                postBean.setPublicador(buscarPublicador(publicacionEnc.getUsernamePublicador()));
                posts.add(postBean);
            }
            return posts;
        }
        return null;
    }

    private PostBean convertirPublicacion(Publicacion publicacion ){
        PostBean bean = new PostBean();
        bean.setCategoria(publicacion.getCategoria());
        ContenidoBean contenido = new ContenidoBean();
        contenido.setDescripcion(publicacion.getContenido());
        
        if(publicacion.getImagen() != null){
            ImagenBean imagen = ConversorImagen.convertirAImagenBean(publicacion.getImagen());
            contenido.setImagen(imagen);
        }
        
        bean.setContenido(contenido);
        bean.setFechaCreacion(publicacion.getFechaCreacion());
        bean.setNumPost(publicacion.getNumPost());
        bean.setUsernamePublicador(publicacion.getUsernamePublicador());
        
        return bean;
    }

    private Publicacion convertirBeanPublicacion(PostBean bean){
        Publicacion publicacion = (Publicacion)FactoryPost.crearPost(FactoryPost.PUBLICACION);
        if(bean.getCategoria() != null)
            publicacion.setCategoria(bean.getCategoria());
        if(bean.getUltimaModificacion() != null)
            publicacion.setUltimaModificacion(bean.getUltimaModificacion());
        if(bean.getNumPost() != null)
            publicacion.setNumPost(bean.getNumPost());
        if(bean.getUsernamePublicador()!= null){
            //String username = obtenerUsernamePublicador(bean.getPublicador());
            publicacion.setUsernamePublicador(bean.getUsernamePublicador());
        }
        
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
        bean.setImagen(ConversorImagen.convertirAImagenBean(usuario.getImagen()));
        bean.setUsername(usuario.getUsername());
        return bean;
    }
    
    private Usuario convertirBeanUsuario(UsuarioBean bean) {
        Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
        usuario.setUsername(bean.getUsername());
        return usuario;
    }
}
