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
import com.mongodb.client.result.UpdateResult;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Comentario;
import entidades.Publicacion;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.bson.conversions.Bson;

/**
 * Clase de objeto de acceso a datos que permite realizar consultas, escrituras,
 * y modificaciones a la colección de publicaciones de usuarios en una base de
 * datos MongoDB
 *
 * @author Juventino López García - 00000248547
 */
public class PublicacionDAO implements IPublicacionDAO {

    private IConexion conexion;
    private MongoCollection<Publicacion> publicaciones;
    private ComentarioDAO comentarioDAO;
    /**
     * Constructor por defecto que establece la conexión a la colección de
     * publicaciones
     */
    public PublicacionDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        publicaciones = baseDatos.getCollection("publicaciones", Publicacion.class);
        comentarioDAO = new ComentarioDAO();
    }

    /**
     * Añade una nueva publicación a la colección de publicaciones
     *
     * @param publicacion Nueva publicación a añadir a la base de datos
     * @throws PersistenciaException Si ocurre un error al insertar la
     * publicación a la colección
     */
    @Override
    public void publicarNuevaPublicacion(Publicacion publicacion) throws PersistenciaException {
        try {
            publicacion.setCodigo(generarNumeroAleatorio());
            publicaciones.insertOne(publicacion);
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo guardar la noticia.");
        }
    }

    /**
     * Busca la publicación dada por el parametro en la colección de
     * publicaciones
     *
     * @param publicacion Publicación con el número de post que se espera
     * encontrar en la colección
     * @return Publicación cuyo número de post coincide con el del parámetro
     * @throws PersistenciaException Si ocurre un error al hacer la búsqueda en
     * la colección
     */
    @Override
    public Publicacion buscarPublicacion(Publicacion publicacion) throws PersistenciaException {
        List<Bson> pipeline = new ArrayList<>();
        pipeline.add(match(eq("codigo", publicacion.getCodigo())));
        // Lookup siempre presente
        pipeline.add(project(fields(
                include( "categoria", "codigo", "fechaCreacion", "ultimaModificacion", 
                        "contenido", "imagen", "usernamePublicador","comentarios","cantidadLikes")
        )));

        return publicaciones
                .aggregate(pipeline, Publicacion.class)
                .into(new ArrayList<>())
                .stream()
                .findFirst()
                .orElse(null); // O lanzar una excepción si no se encuentra
    }

    /**
     * Busca las publicaciones cuya categoria coincide con la categoria del
     * objeto del parámtero
     *
     * @param publicacion Publicación con la categoría de publicaciones que se
     * busca
     * @return Lista con las publicaciones con la categoria coincidente
     * @throws PersistenciaException Si ocurre un error al hacer la búsqueda en
     * la colección
     */
    @Override
    public List<Publicacion> buscarPublicacionesPorCategoria(Publicacion publicacion) throws PersistenciaException {
        List<Bson> pipeline = new ArrayList<>();
        pipeline.add(match(eq("categoria", publicacion.getCategoria())));
        return publicaciones
                .aggregate(pipeline, Publicacion.class)
                .into(new ArrayList<>());
    }

    /**
     * Actualiza una publicación existente en la colección de publicaciones con
     * la información del objeto del parámetro
     *
     * @param publicacion Publicación con el número de post de la publicación a
     * actualizar y la información actualizada
     * @throws PersistenciaException Si ocurre un error al hacer la
     * actualización en la colección
     */
    @Override
    public boolean actualizarPublicacion(Publicacion publicacion) throws PersistenciaException {
        Bson filtro = Filters.eq("numPost", publicacion.getCodigo());
        Bson actualizar;

        actualizar = Updates.combine(
                Updates.set("ultimaModificacion", new Date()),
                Updates.set("urlImg", publicacion.getUrlImg()),
                Updates.set("contenido", publicacion.getContenido())
        );

        return publicaciones.updateOne(filtro, actualizar).getModifiedCount()>0;
    }
    
    @Override
    public boolean actualizarLikesPublicacion(Publicacion publicacion){
        Bson filtro = Filters.eq("codigo", publicacion.getCodigo());
        Bson actualizar = Updates.set("likes", publicacion.getCantidadLikes());
        UpdateResult result = publicaciones.updateOne(filtro, actualizar);
        return result.getModifiedCount() >0;
    }
    
    @Override
    public boolean agregarComentarioPublicacion(Publicacion publicacion, Comentario comentario){
        Bson filtro = Filters.eq("numPost", publicacion.getCodigo());
        Bson actualizar = Updates.addToSet("comentarios", comentario.getIdComentario());
        UpdateResult result = publicaciones.updateOne(filtro, actualizar);
        return result.getModifiedCount() >0;
    }

    @Override
    public boolean removerComentarioPublicacion(Publicacion publicacion, Comentario comentario){
        Bson filtro = Filters.eq("numPost", publicacion.getCodigo());
        Bson actualizar = Updates.pull("comentarios", comentario.getIdComentario());
        UpdateResult result = publicaciones.updateOne(filtro, actualizar);
        return result.getModifiedCount() > 0;
    }
    
    /**
     * Regresa todas las publicaciones contenidas en la colección de
     * publicaciones
     *
     * @return Lista con las publicaciones de la colección
     */
    @Override
    public List<Publicacion> buscarPublicaciones() {
        List<Bson> pipeline = new ArrayList<>();

        // No hay lookup ni unwind ya que no se une a otra colección
        // Project stage para incluir solo los campos deseados
        pipeline.add(project(fields(
                include("categoria", "codigo", "fechaCreacion",
                        "contenido","imagen", "usernamePublicador")
        )));

        // Realizar la agregación
        return publicaciones
                .aggregate(pipeline, Publicacion.class)
                .into(new ArrayList<>());
    }

    /**
     * Genera un número aleatorio de 10 dígitos y lo retorna como una cadena de
     * caracteres.
     *
     * El número generado es precedido por la letra "N" y se rellena con ceros a
     * la izquierda si es necesario para completar los 10 dígitos.
     *
     * @return Cadena de caracteres que representa el número aleatorio generado.
     */
    private String generarNumeroAleatorio() {
        Random random = new Random();

        long numero = random.nextLong() % 10000000000L; // Limita el rango a 10 cifras

        // Formatea el número a una cadena de 10 caracteres, rellenando con ceros a la izquierda
        return "N" + String.format("%010d", Math.abs(numero)); // Usar Math.abs para evitar números negativos
    }

    /**
     * Elimina una publicación de la colección de publicaciones
     *
     * @param publicacion Publicación a eliminar de la colección
     */
    @Override
    public boolean eliminarPublicacion(Publicacion publicacion) {
        Bson filtro = Filters.eq("numPost", publicacion.getCodigo());
        System.out.println("Se elimino las publicaciones: " + publicacion.getCodigo());
        return publicaciones.deleteOne(filtro).getDeletedCount()>0;
    }
}
