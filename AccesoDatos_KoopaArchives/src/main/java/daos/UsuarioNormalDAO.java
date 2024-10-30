package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.util.Random;

/**
 *
 * @author luisa M
 */
public class UsuarioNormalDAO implements IUsuarioDAO{
    private IConexion conexion;
    private final MongoCollection<Usuario> usuariosNormales;
    
    public UsuarioNormalDAO(){
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        usuariosNormales = baseDatos.getCollection("UsuariosNormales", Usuario.class);
    }
    
    @Override
    public Usuario registrarUsuario(Usuario usuario) throws PersistenciaException{
        try {
            usuario.setIdUsuario(generarNumeroAleatorio());
            usuariosNormales.insertOne(usuario);
            return usuario;
        } catch (MongoException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al agregar el usuario");
        }
    }
    
    @Override
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException{
        try {
            Usuario user = usuariosNormales.find(Filters.eq("idUsuario",usuario.getIdUsuario())).first();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al buscar el usuario");
        }
    }
    
    private String generarNumeroAleatorio() {
        Random random = new Random();

         long numero = random.nextLong() % 10000000000L; // Limita el rango a 10 cifras

        // Formatea el número a una cadena de 10 caracteres, rellenando con ceros a la izquierda
        return "N"+String.format("%010d", Math.abs(numero)); // Usar Math.abs para evitar números negativos
    }
}
