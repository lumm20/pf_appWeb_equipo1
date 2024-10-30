package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.unwind;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.model.Updates;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Comentario;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.bson.conversions.Bson;

public class ComentarioDAO implements IComentarioDAO {

    private final IConexion conexion;
    private final MongoCollection<Comentario> comentarios;

    public ComentarioDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        comentarios = baseDatos.getCollection("comentarios", Comentario.class);
    }

    //Permite publicar un comentario con el id de la noticia
    @Override
    public void registrarComentario(Comentario comentario) throws PersistenciaException {
        try {
            Comentario aux = new Comentario();
            aux.setContenido(comentario.getContenido());
            aux.setIdNoticia(comentario.getNoticia().getNumPost());
            aux.setIdUsuario(comentario.getUsuario().getIdUsuario());
            aux.setFechaPublicacion(new Date());
            aux.setIdComentario(generarNumeroAleatorio());
            comentarios.insertOne(aux);
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo guardar el comentario.");
        }
    }

    //Permite buscar un comentario con el idComentario
    @Override
    public Comentario buscarComentario(Comentario comentario) throws PersistenciaException {
        try {
            return comentarios.find(eq("idComentario", comentario.getIdComentario())).first();
        } catch (MongoException me) {
            throw new PersistenciaException("No se encontró el comentario");
        }
    }

    //Permite eliminar un comentario por Id
    @Override
    public void eliminarComentario(Comentario comentario) throws PersistenciaException {
        try {

            DeleteResult resultado = comentarios.deleteOne(eq("idComentario", comentario.getIdComentario()));
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo eliminar el comentario.");
        }
    }

    //Permite anclar un comentario por id
    @Override
    public void anclarComentario(Comentario comentario) throws PersistenciaException {
        try {
            comentarios.updateOne(eq("idComentario", comentario.getIdComentario()), set("anclado", true));
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo anclar el comentario.");
        }
    }

    //Permite editar un comentario
    @Override
    public void editarComentario(Comentario comentario) throws PersistenciaException {
        try {
            Bson filtro = Filters.eq("idComentario", comentario.getIdComentario());
            Bson actualizar;

            actualizar = Updates.combine(
                    Updates.set("fechaModificacion", new Date()),
                    Updates.set("contenido", comentario.getContenido())
            );

            comentarios.updateOne(filtro, actualizar);
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo actualizar el comentario.");
        }
    }

    //Permite obtener los comentarios por el idNoticia
    @Override
    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia) throws PersistenciaException {
        try {
            List<Bson> pipeline = new ArrayList<>();

            // Stage 1: Match por idNoticia
            pipeline.add(match(eq("idNoticia", noticia.getNumPost())));

            // Stage 2: Lookup para unir con UsuariosNormales
            pipeline.add(lookup("UsuariosNormales",
                    "idUsuario",
                    "idUsuario",
                    "usuario"));

            // Stage 3: Unwind del array resultante del lookup
            pipeline.add(unwind("$usuario"));

            // Stage 4: Project para seleccionar los campos
            pipeline.add(project(fields(
                    include("idComentario",
                            "contenido",
                            "fechaPublicacion",
                            "idNoticia",
                            "idUsuario",
                            "usuario")
            )));

            return comentarios
                    .aggregate(pipeline, Comentario.class)
                    .into(new ArrayList<>()); // O podrías lanzar una excepción si no se encuentra
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudieron obtener los comentarios.");
        }
    }

    private String generarNumeroAleatorio() {
        Random random = new Random();

        long numero = random.nextLong() % 10000000000L; // Limita el rango a 10 cifras

        // Formatea el número a una cadena de 10 caracteres, rellenando con ceros a la izquierda
        return "N" + String.format("%010d", Math.abs(numero)); // Usar Math.abs para evitar números negativos
    }
}
