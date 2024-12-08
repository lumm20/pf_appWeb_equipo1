package objetosNegocio;

import daos.IUsuarioDAO;
import daos.UsuarioDAO;
import entidades.Image;
import entidades.Usuario;
import entidades_beans.ImagenBean;
import entidades_beans.UsuarioBean;
import entidades_beans.UsuarioRegistroBean;
import excepciones.PersistenciaException;
import fachadas.FacadeUsuario;
import fachadas.IFacadeUsuario;
import factories.FactoryUser;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.Binary;
import utilities.ConversorImagen;

/**
 *
 * @author karim
 */
public class UsuarioBO implements IUsuarioBO{
    private IFacadeUsuario facadeUsuario;
    

    public UsuarioBO() {
        this.facadeUsuario = new FacadeUsuario();
    }

    @Override
    public boolean registrarUsuario(UsuarioRegistroBean usuarioBean) {
      Usuario usuario = convertirUsuarioDAO(usuarioBean);
      boolean flag = false;
        try {
            flag = facadeUsuario.registrarUsuario(usuario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(UsuarioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    @Override
    public UsuarioRegistroBean existeUsuario(UsuarioRegistroBean usuarioBean){
        Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
        usuario.setUsername(usuarioBean.getUsername());
        usuario.setEmail(usuarioBean.getEmail());
        try {
            Usuario usuarioEncontrado = facadeUsuario.existeUsuario(usuario);
            if(usuarioEncontrado != null){
                return convertirABeanRegistro(usuarioEncontrado);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(UsuarioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public UsuarioRegistroBean buscarUsuario(UsuarioRegistroBean usuarioBean) {
        Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
        usuario.setUsername(usuarioBean.getUsername());
        try {
            Usuario usuarioEncontrado = facadeUsuario.buscarUsuario(usuario);
            if(usuarioEncontrado != null){
                return convertirABeanRegistro(usuarioEncontrado);
            }
        } catch (PersistenciaException e) {
            Logger.getLogger(UsuarioBO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public UsuarioBean iniciarSesion(UsuarioBean usuario) {
        Usuario u = convertirUsuarioDAO(usuario);
        Usuario encontrado;
        try {
            encontrado = facadeUsuario.iniciarSesion(u);
            return convertirABeanInicio(encontrado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(UsuarioBO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public UsuarioRegistroBean hardcodearAdmin(UsuarioRegistroBean usuarioBean) {
        Usuario usuario = crearAdmin(usuarioBean);
        try {
            facadeUsuario.registrarUsuario(usuario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(UsuarioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioBean;
    }
    
    
    
    private Usuario convertirUsuarioDAO(UsuarioBean bean){
       Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
       usuario.setUsername(bean.getUsername());
       usuario.setPassword(bean.getPassword());
       
       return usuario;
    }

    private Usuario convertirUsuarioDAO(UsuarioRegistroBean bean){
       Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
       usuario.setUsername(bean.getUsername());
       usuario.setPassword(bean.getPassword());
       usuario.setEmail(bean.getEmail());
       usuario.setApellidoMaterno(bean.getApellidoMaterno());
       usuario.setApellidoPaterno(bean.getApellidoPaterno());
       //usuario.setCumpleanos(bean.getCumpleanos());
       usuario.setGenero(bean.getGenero());
       
       //Convertir imagen
       Image image = ConversorImagen.convertirAImagenDAO(bean.getImagen());
       usuario.setImagen(image);
       
       return usuario;
    }
    private Usuario crearAdmin(UsuarioRegistroBean bean){
       Usuario usuario = FactoryUser.crearUsuario(FactoryUser.ADMIN);
       usuario.setUsername(bean.getUsername());
       usuario.setPassword(bean.getPassword());
       usuario.setEmail(bean.getEmail());
       usuario.setApellidoMaterno(bean.getApellidoMaterno());
       usuario.setApellidoPaterno(bean.getApellidoPaterno());
       //usuario.setCumpleanos(bean.getCumpleanos());
       usuario.setGenero(bean.getGenero());
       
       //Convertir imagen
//       Image image = new Image();
//       image.setFechaSubida(new Date());
//       image.setNombreArchivo(bean.getImagen().getNombreArchivo());
//       image.setTipoImagen(bean.getImagen().getTipoImagen());
//       image.setContent(new Binary(bean.getImagen().getImageBytes()));
       Image image = ConversorImagen.convertirAImagenDAO(bean.getImagen());
       usuario.setImagen(image);
       
       return usuario;
    }
    
    
    private UsuarioBean convertirABeanInicio(Usuario usuario){
        UsuarioBean bean = new UsuarioBean();
        bean.setUsername(usuario.getUsername());
        bean.setRol(usuario.getRol());
        System.out.println(usuario.getRol());
        
        ImagenBean imagen = ConversorImagen.convertirAImagenBean2(usuario.getImagen());
        bean.setImagen(imagen);
        
        return bean;
    }
    
    private UsuarioRegistroBean convertirABeanRegistro(Usuario usuario){
        UsuarioRegistroBean bean = new UsuarioRegistroBean();
        bean.setUsername(usuario.getUsername());
        bean.setNombre(usuario.getNombre());
        bean.setGenero(usuario.getGenero());
        bean.setEmail(usuario.getEmail());
        bean.setApellidoMaterno(usuario.getApellidoMaterno());
        bean.setApellidoPaterno(usuario.getApellidoPaterno());
        
        
        
        return bean;
    }
}
