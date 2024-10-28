package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.IConexion;
import entidades.Comentario;
import excepciones.PersistenciaException;

public class ComentarioDAO implements IComentarioDAO{
    private IConexion conexion;
    private MongoCollection<Comentario> noticias;
    
    public ComentarioDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        noticias = baseDatos.getCollection("comentarios", Comentario.class);
    }
    
    @Override
    public void publicarComentario(Comentario comentario) throws PersistenciaException {
        
    }
}
