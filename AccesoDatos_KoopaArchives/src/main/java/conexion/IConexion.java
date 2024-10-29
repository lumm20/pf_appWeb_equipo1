package conexion;

import com.mongodb.client.MongoDatabase;

/**
 * La interfaz IConexion define el contrato para establecer una conexión
 * con una base de datos MongoDB.
 * 
 * @author José Karim Franco Valencia - 245138
 */
public interface IConexion {
    /**
     * Crea y establece una conexión con una base de datos MongoDB.
     * 
     * @return Una instancia de MongoDatabase que representa la conexión 
     *         establecida con la base de datos.
     */
    public MongoDatabase crearConexion();
    
    public MongoDatabase obtenerBaseDatos();
}

