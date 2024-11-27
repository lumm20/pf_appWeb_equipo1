/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import entidades.Contenido;
import entidades.Image;
import entidades.Noticia;
import entidades.Publicacion;
import entidades.Subtema;
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
    public boolean subirNoticia(PostBean post) {
        try {
            Contenido contenido = convertirBeanContenido(post.getContenido());
            Noticia noticia = convertirBeanNoticia(post);
            facadePost.registrarNoticia(noticia, contenido);
            return true;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PostBO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
    public boolean eliminarNoticia(PostBean post) {
        return true;
    }

    @Override
    public boolean eliminarPublicacion(PostBean post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PostBean buscarNoticia(PostBean post) {
        Noticia noticia = facadePost.buscarNoticia(convertirBeanNoticia(post));
        if(noticia != null){
            PostBean bean = convertirNoticia(noticia);
            return bean;
        }
        return null;
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

    @Override
    public List<PostBean> buscarNoticiasConFiltro(FiltroBusquedaBean filtro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private PostBean convertirNoticia(Noticia noticia ){
        PostBean bean = new PostBean();
        bean.setCategoria(noticia.getCategoria());
        bean.setContenido(convertirContenido(noticia.getContenido()));
        bean.setFechaCreacion(noticia.getFechaCreacion());
        bean.setNumPost(noticia.getNumPost());
        UsuarioBean publicador = new UsuarioBean();
        publicador.setUsername(noticia.getUsernamePublicador());
        bean.setPublicador(publicador);
        
        return bean;
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
    private ContenidoBean convertirContenido(Contenido contenido){
        ContenidoBean bean = new ContenidoBean();
        bean.setDescripcion(contenido.getDescripcion());
        bean.setTitulo(contenido.getTitulo());
        if(contenido.getImagen() != null){
            ImagenBean imagen = ConversorImagen.convertirAImagenBean(contenido.getImagen());
            bean.setImagen(imagen);
        }
        return bean;
    }
    private Contenido convertirBeanContenido(ContenidoBean bean){
        Contenido contenido = new Contenido();
        contenido.setDescripcion(bean.getDescripcion());
        contenido.setTitulo(bean.getTitulo());
        if(bean.getImagen()!= null){
            Image imagen = ConversorImagen.convertirAImagenDAO(bean.getImagen());
            contenido.setImagen(imagen);
        }
        if(bean.getSubtemas() != null && !bean.getSubtemas().isEmpty())
            contenido.setSubtemas(convertirBeansSubtema(bean.getSubtemas()));
        return contenido;
    }
    
    private Subtema convertirBeanSubtema(SubtemaBean bean){
        Subtema subtema = new Subtema();
        subtema.setDescripcion(bean.getDescripcion());
        subtema.setSubtitulo(bean.getSubtitulo());
        if(bean.getImagen() != null){
            Image imagen = ConversorImagen.convertirAImagenDAO(bean.getImagen());
            subtema.setImagen(imagen);
        }
        return subtema;
    }
    
    private List<Subtema> convertirBeansSubtema(List<SubtemaBean> beans){
        List<Subtema> subtemas = new ArrayList<>();
        for (SubtemaBean bean : beans) {
            subtemas.add(convertirBeanSubtema(bean));
        }
        return subtemas;
    }
    
    private Noticia convertirBeanNoticia(PostBean bean){
        Noticia noticia = (Noticia)FactoryPost.crearPost(FactoryPost.NOTICIA);
        if(bean.getCategoria() != null)
            noticia.setCategoria(bean.getCategoria());
        if(bean.getFechaCreacion() != null)
            noticia.setFechaCreacion(bean.getFechaCreacion());
        if(bean.getUltimaModificacion()!= null)
            noticia.setUltimaModificacion(bean.getUltimaModificacion());
        noticia.setNumPost(bean.getNumPost());
        noticia.setAnclada(bean.isAnclada());
        return noticia;
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
        
        ContenidoBean contenidoBean = bean.getContenido();
        if(contenidoBean.getImagen() != null)
            publicacion.setImagen(ConversorImagen.convertirAImagenDAO(contenidoBean.getImagen()));
        publicacion.setContenido(bean.getContenido().getDescripcion());
        
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
