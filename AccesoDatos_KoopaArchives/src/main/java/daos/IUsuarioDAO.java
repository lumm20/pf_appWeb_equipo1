package daos;

//import entidades.UsuarioNormal;
//import excepciones.PersistenciaException;

/**
 *
 * @author José Karim Franco Valencia - 245138
 */
public interface IUsuarioDAO {

    public void registrarUsuario(UsuarioNormal usuario)  throws PersistenciaException;
    public UsuarioNormal actualizarUsuario();
}
//    public void registrarUsuario(UsuarioNormal usuario)  throws PersistenciaException;
