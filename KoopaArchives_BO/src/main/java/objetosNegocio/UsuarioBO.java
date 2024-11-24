package objetosNegocio;

import daos.IUsuarioDAO;
import daos.UsuarioDAO;
import entidades.Image;
import entidades.Usuario;
import entidades_beans.UsuarioBean;
import entidades_beans.UsuarioRegistroBean;
import excepciones.PersistenciaException;
import fachadas.FacadeUsuario;
import fachadas.IFacadeUsuario;
import factories.FactoryUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.Binary;

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
    public void registrarUsuario(UsuarioRegistroBean usuarioBean) {
      Usuario usuario = convertirUsuarioDAO(usuarioBean);
        try {
            facadeUsuario.registrarUsuario(usuario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(UsuarioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public UsuarioRegistroBean buscarUsuario(UsuarioRegistroBean usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioBean iniciarSesion(UsuarioBean usuario) {
        Usuario u = convertirUsuarioDAO(usuario);
        System.out.println(u);
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
    public UsuarioRegistroBean hardcodearAdmin(UsuarioRegistroBean usuario) {
        return null;
    }
    
    
    
    private Usuario convertirUsuarioDAO(UsuarioBean bean){
       Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
       usuario.setUsername(bean.getUsername());
       usuario.setPassword(bean.getPassword());
       
       //Convertir imagen
//       Image image = new Image();
//       image.setFechaSubida(bean.getImagen().getFechaSubida());
//       image.setNombreArchivo(bean.getImagen().getNombreArchivo());
//       image.setTipoImagen(bean.getImagen().getTipoImagen());
//       image.setContent(new Binary(bean.getImagen().getImageBytes()));
       
       return usuario;
    }

    private Usuario convertirUsuarioDAO(UsuarioRegistroBean bean){
       Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
       usuario.setUsername(bean.getUsername());
       usuario.setPassword(bean.getPassword());
       usuario.setEmail(bean.getEmail());
       usuario.setApellidoMaterno(bean.getApellidoMaterno());
       usuario.setApellidoPaterno(bean.getApellidoPaterno());
       usuario.setPassword(bean.getPassword());
       usuario.setCumpleanos(bean.getCumpleanos());
       usuario.setGenero(bean.getGenero());
       
       //Convertir imagen
//       Image image = new Image();
//       image.setFechaSubida(bean.getImagen().getFechaSubida());
//       image.setNombreArchivo(bean.getImagen().getNombreArchivo());
//       image.setTipoImagen(bean.getImagen().getTipoImagen());
//       image.setContent(new Binary(bean.getImagen().getImageBytes()));
       
       return usuario;
    }
    
    
    private UsuarioBean convertirABeanInicio(Usuario usuario){
        UsuarioBean bean = new UsuarioBean();
        bean.setUsername(usuario.getUsername());
        bean.setRol(usuario.getRol());
        return bean;
    }
}
