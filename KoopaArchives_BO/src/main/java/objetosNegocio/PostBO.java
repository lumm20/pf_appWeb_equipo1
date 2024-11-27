package objetosNegocio;

import entidades.Noticia;
import entidades.Publicacion;
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

    @Override
    public PostBean buscarPublicacion(PostBean post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    private Publicacion convertirBeanPublicacion(PostBean bean){
        Publicacion publicacion = (Publicacion)FactoryPost.crearPost(FactoryPost.PUBLICACION);
        if(bean.getCategoria() != null)
            publicacion.setCategoria(bean.getCategoria());
        if(bean.getUltimaModificacion() != null)
            publicacion.setUltimaModificacion(bean.getUltimaModificacion());
        if(bean.getNumPost() != null)
            publicacion.setCodigo(bean.getNumPost());
        if(bean.getPublicador() != null){
            String username = obtenerUsernamePublicador(bean.getPublicador());
//            publicacion.setUsernamePublicador(username);
        }
        publicacion.setFechaCreacion(bean.getFechaCreacion());
//        publicacion.setContenido(bean.getContenido().getDescripcion());
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
