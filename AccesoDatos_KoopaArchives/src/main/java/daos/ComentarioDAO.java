package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Comentario;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

public class ComentarioDAO implements IComentarioDAO {

    private IConexion conexion;
    private MongoCollection<Comentario> comentarios;

    public ComentarioDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        comentarios = baseDatos.getCollection("comentarios", Comentario.class);
    }

    //Permite publicar un comentario con el id de la noticia
    public void publicarComentario(Comentario comentario, ObjectId idNoticia) throws PersistenciaException {
        try {
            comentario.setIdNoticia(idNoticia);
            InsertOneResult resultado = comentarios.insertOne(comentario);
            ObjectId id = resultado.getInsertedId().asObjectId().getValue();
            comentario.setIdComentario(id);
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo guardar el comentario.");
        }
    }

    //Permite buscar un comentario con el idComentario
    public Comentario buscarComentario(ObjectId idComentario) throws PersistenciaException {
        try {
            return comentarios.find(eq("_id", idComentario)).first();
        } catch (MongoException me) {
            throw new PersistenciaException("No se encontró el comentario");
        }
    }

    //Permite eliminar un comentario por Id
    public boolean eliminarComentarioPorId(ObjectId idComentario) throws PersistenciaException {
        try {
            DeleteResult resultado = comentarios.deleteOne(eq("_id", idComentario));
            return resultado.getDeletedCount() > 0; // Retorna true si se eliminó, sino falso
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo eliminar el comentario.");
        }
    }

    //Permite anclar un comentario por id
    public void anclarComentario(ObjectId idComentario) throws PersistenciaException {
        try {
            comentarios.updateOne(eq("_id", idComentario), set("destacado", true));
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo anclar el comentario.");
        }
    }

    //Permite editar un comentario
    public void editarComentario(ObjectId idComentario, String nuevoComentario) throws PersistenciaException {
        try {
            comentarios.updateOne(
                    eq("_id", idComentario),
                    set("contenido", nuevoComentario)
            );
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo actualizar el comentario.");
        }
    }

    //Permite obtener los comentarios por el idNoticia
    public List<Comentario> obtenerComentariosPorNoticia(ObjectId idNoticia) throws PersistenciaException {
        try {
            return comentarios.find(eq("idNoticia", idNoticia)).into(new ArrayList<>());
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudieron obtener los comentarios.");
        }
    }

    // Método para ajustar la fecha de inicio a las 00:00:00
    private Date ajustarFechaInicio(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // Método para ajustar la fecha de fin a las 23:59:59
    private Date ajustarFechaFin(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
