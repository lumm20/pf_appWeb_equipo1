/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import entidades.Contenido;
import entidades.Noticia;
import entidades.Publicacion;
import entidades.Subtema;
import entidades.Usuario;
import entidades_beans.ContenidoBean;
import entidades_beans.FiltroBusquedaBean;
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

    @Override
    public PostBean buscarPublicacion(PostBean post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    private ContenidoBean convertirContenido(Contenido contenido){
        ContenidoBean bean = new ContenidoBean();
        bean.setDescripcion(contenido.getDescripcion());
        bean.setTitulo(contenido.getTitulo());
        bean.setUrlImg(contenido.getUrlImg());
        return bean;
    }
    private Contenido convertirBeanContenido(ContenidoBean bean){
        Contenido contenido = new Contenido();
        contenido.setDescripcion(bean.getDescripcion());
        contenido.setTitulo(bean.getTitulo());
        if(bean.getUrlImg() != null)
            contenido.setUrlImg(bean.getUrlImg());
        if(bean.getSubtemas() != null && !bean.getSubtemas().isEmpty())
            contenido.setSubtemas(convertirBeansSubtema(bean.getSubtemas()));
        return contenido;
    }
    
    private Subtema convertirBeanSubtema(SubtemaBean bean){
        Subtema subtema = new Subtema();
        subtema.setDescripcion(bean.getDescripcion());
        subtema.setSubtitulo(bean.getSubtitulo());
        if(bean.getUrl_img() != null)
            subtema.setUrl_img(bean.getUrl_img());
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
        if(bean.getPublicador() != null){
            String username = obtenerUsernamePublicador(bean.getPublicador());
            publicacion.setUsernamePublicador(username);
        }
        publicacion.setFechaCreacion(bean.getFechaCreacion());
        publicacion.setContenido(bean.getContenido().getDescripcion());
        return publicacion;
    }
    
    private String obtenerUsernamePublicador(UsuarioBean bean){
        Usuario user = convertirUsuario(bean);
        try {
            user = facadeUsuario.buscarUsuario(user);
            String username = user.getUsername();
            return username;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PostBO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private Usuario convertirUsuario(UsuarioBean bean) {
        Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
        usuario.setUsername(bean.getUsername());
        return usuario;
    }
}
