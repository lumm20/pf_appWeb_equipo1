package daos;

//import entidades.UsuarioNormal;

import entidades.Usuario;
import excepciones.PersistenciaException;

//import excepciones.PersistenciaException;

/**
 *
 * @author José Karim Franco Valencia - 245138
 */
public interface IUsuarioDAO {

    public Usuario registrarUsuario(Usuario usuario)  throws PersistenciaException;
    public Usuario buscarUsuario(Usuario usuario)throws PersistenciaException;
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaException;
}
//    public void registrarUsuario(UsuarioNormal usuario)  throws PersistenciaException;
