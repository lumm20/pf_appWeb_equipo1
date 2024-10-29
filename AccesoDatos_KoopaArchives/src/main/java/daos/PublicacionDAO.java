/*
 * PublicacionDAO.java
 */
package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.model.Updates;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Publicacion;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Juventino López García - 00000248547 - 28/10/2024
 */
public class PublicacionDAO implements IPublicacionDAO {

    private IConexion conexion;
    private MongoCollection<Publicacion> publicaciones;

    public PublicacionDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        publicaciones = baseDatos.getCollection("publicaciones", Publicacion.class);
    }

    @Override
    public void publicarNuevaPublicacion(Publicacion publicacion) throws PersistenciaException {
        try {
            ObjectId id = new ObjectId();
            publicacion.setNumPost(generarNumeroAleatorio());
            publicaciones.insertOne(publicacion);
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo guardar la noticia.");
        }
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion publicacion) throws PersistenciaException {
        List<Bson> pipeline = new ArrayList<>();
        pipeline.add(match(eq("numPost", publicacion.getNumPost())));
        // Lookup siempre presente
        pipeline.add(project(fields(
                include("_id", "anclada", "categoria", "numPost", "fechaCreacion", "ultimaModificacion", "contenido", "urlImg")
        )));

        return publicaciones
                .aggregate(pipeline, Publicacion.class)
                .into(new ArrayList<>())
                .stream()
                .findFirst()
                .orElse(null); // O lanzar una excepción si no se encuentra
    }

    @Override
    public List<Publicacion> buscarPublicaciones(Publicacion publicacion) throws PersistenciaException {
        List<Bson> pipeline = new ArrayList<>();
        pipeline.add(match(eq("categoria", publicacion.getCategoria())));
        return publicaciones
                .aggregate(pipeline, Publicacion.class)
                .into(new ArrayList<>());
    }

    @Override
    public void actualizarPublicacion(Publicacion publicacion) throws PersistenciaException {
        Bson filtro = Filters.eq("numPost", publicacion.getNumPost());
        Bson actualizar;

        actualizar = Updates.combine(
                Updates.set("ultimaModificacion", new Date()),
                Updates.set("urlImg", publicacion.getUrlImg()),
                Updates.set("contenido", publicacion.getContenido())
        );

        publicaciones.updateOne(filtro, actualizar);
    }

    @Override
    public List<Publicacion> buscarPublicaciones() {
        List<Bson> pipeline = new ArrayList<>();

        // No hay lookup ni unwind ya que no se une a otra colección
        // Project stage para incluir solo los campos deseados
        pipeline.add(project(fields(
                include("_id", "anclada", "categoria", "numPost", "fechaCreacion", "ultimaModificacion", "contenido", "urlImg")
        )));

        // Realizar la agregación
        return publicaciones
                .aggregate(pipeline, Publicacion.class)
                .into(new ArrayList<>());
    }

    private String generarNumeroAleatorio() {
        Random random = new Random();

        long numero = random.nextLong() % 10000000000L; // Limita el rango a 10 cifras

        // Formatea el número a una cadena de 10 caracteres, rellenando con ceros a la izquierda
        return "N" + String.format("%010d", Math.abs(numero)); // Usar Math.abs para evitar números negativos
    }

    @Override
    public void eliminarPublicacion(Publicacion publicacion) {
        Bson filtro = Filters.eq("numPost", publicacion.getNumPost());
        System.out.println("Se elimino las publicaciones: " + publicacion.getNumPost());
        publicaciones.deleteOne(filtro);
    }
}
