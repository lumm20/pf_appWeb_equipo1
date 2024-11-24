package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaException;
import org.bson.conversions.Bson;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author luisa M
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexion conexion;
    private final MongoCollection<Usuario> coleccionUsuarios;

    public UsuarioDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        coleccionUsuarios = baseDatos.getCollection("usuarios", Usuario.class);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws PersistenciaException {
        try {
            // Generar un salt y encriptar la contraseña
            String passwordHash = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());

            // Actualizar el usuario con la contraseña encriptada
            usuario.setPassword(passwordHash);

            coleccionUsuarios.insertOne(usuario);

            return usuario;
        } catch (MongoException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al agregar el usuario");
        }
    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException {
        try {
            Usuario user = coleccionUsuarios.find(Filters.eq("username", usuario.getUsername())).first();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al buscar el usuario");
        }
    }

    @Override
    public Usuario iniciarSesion(Usuario usuario) throws PersistenciaException {
        try {
            // Buscar solo por username ya que la contraseña está encriptada
            Usuario usuarioEncontrado = buscarUsuario(usuario);

            // Si encontramos el usuario, verificar la contraseña
            if (usuarioEncontrado != null) {
                // Verificar si la contraseña coincide con el hash almacenado
                if (BCrypt.checkpw(usuario.getPassword(), usuarioEncontrado.getPassword())) {
                    return usuarioEncontrado;
                }
            }

            return null; // Usuario no encontrado o contraseña incorrecta

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Error al buscar el usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario existeUsuario(Usuario usuario) throws PersistenciaException {
        try {
        // Crear un filtro que busque por ambos campos: username y password
        Bson filtro = Filters.or(
            Filters.eq("username", usuario.getUsername()),
            Filters.eq("email", usuario.getEmail())
        );
        
        // Buscar el primer usuario que coincida con ambos criterios
        Usuario user = coleccionUsuarios.find(filtro).first();
        
        return user; // Retornará null si no encuentra ninguna coincidencia
        
    } catch (Exception e) {
        System.out.println(e.getMessage());
        throw new PersistenciaException("Error al buscar el usuario: " + e.getMessage());
    }
    }
}
