package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.DateCodec;
import java.util.TimeZone;
import java.util.Arrays;
import java.util.Date;
import java.time.ZonedDateTime;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;

public class Conexion implements IConexion{
    private static Conexion conexion;
    private static MongoDatabase baseDatos;
    
    private Conexion() {
    }
    
    public static Conexion getInstance() {
        if (conexion == null) {
            conexion = new Conexion();
            baseDatos = conexion.crearConexion();
        }
        return conexion;
    }
    
    /**
     * Método para crear y establecer una conexión con una base de datos MongoDB.
     * Incluye configuración de zona horaria y conversores personalizados para fechas.
     * 
     * @return Una instancia de MongoDatabase que representa la conexión 
     *         establecida con la base de datos.
     */
    @Override
    public MongoDatabase crearConexion() {
        String cadenaConexion = "mongodb://127.0.0.1:27017";
        String nombreBaseDatos = "koopaArchives";
        
        // Configurar zona horaria por defecto
        TimeZone.setDefault(TimeZone.getTimeZone("America/Hermosillo")); // Ajusta a tu zona horaria
        
        // Crear codec registry con soporte para fechas
        CodecRegistry pojoCodecRegistry = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromCodecs(new DateTimeCodec()),  // Registrar nuestro codec personalizado
            fromProviders(PojoCodecProvider.builder()
                .register("entidades")  // Registrar el paquete donde están tus POJOs
                .build())
        );
        
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(cadenaConexion))
                .codecRegistry(pojoCodecRegistry)
                .build();
                
        MongoClient cliente = MongoClients.create(settings);
        MongoDatabase bd = cliente.getDatabase(nombreBaseDatos);
        
        return bd;
    }
    
    @Override
    public MongoDatabase obtenerBaseDatos() {
        return baseDatos;
    }
   
}