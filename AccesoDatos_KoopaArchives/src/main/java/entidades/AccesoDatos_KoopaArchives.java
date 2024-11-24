package entidades;

import daos.IUsuarioDAO;
import daos.UsuarioDAO;
import excepciones.PersistenciaException;
import factories.FactoryUser;

/**
 *
 * @author karim
 */
public class AccesoDatos_KoopaArchives {

    public static void main(String[] args) throws PersistenciaException {
        Usuario usuario = FactoryUser.crearUsuario(FactoryUser.NORMAL);
//        usuario.acompletar("Victor", "Encinas", "Guzman", "luffy27", "luffy", "luffy@email.com", new Date(), "Hombre");
//        usuario.acompletar("Victor", "Encinas", "Guzman", "toro4", "toro", "toro@email.com", new Date(), "Hombre");
        IUsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioBuscar = new Usuario();
        usuarioBuscar.setEmail("luffy@emil.com");
        usuarioBuscar.setUsername("toro4");
        Usuario inicio =dao.existeUsuario(usuarioBuscar);
        
        if(inicio != null){
            System.out.println(inicio);
        }else{
            System.out.println("Datos incorrectos");
        }
        
    }
}
