package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.unwind;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import conexion.Conexion;
import conexion.IConexion;
import entidades.FiltroNoticia;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;

/**
 * Clase de acceso a datos para la colección de Noticias de una base de datos de
 * MongoDB que permite realizar consultas, inserciones, actualizaciones y borrar
 * noticias
 *
 * @author karim
 */
public class NoticiaDAO implements INoticiaDAO {

    private IConexion conexion;
    private MongoCollection<Noticia> noticias;

    /**
     * Constructor de la clase NoticiaDAO.
     */
    public NoticiaDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        noticias = baseDatos.getCollection("noticias", Noticia.class);
    }

    /**
     * Publica una nueva noticia en la base de datos.
     *
     * @param noticia Noticia a publicar.
     * @return
     * @throws PersistenciaException Si ocurre un error al guardar la noticia.
     */
    @Override
    public Noticia registrarNoticia(Noticia noticia) throws PersistenciaException {
        try {
            noticia.setCodigo(generarNumeroAleatorio());
            noticias.insertOne(noticia);
            return noticia;
        } catch (MongoException me) {
            noticia.setCodigo(null);
            throw new PersistenciaException("No se pudo guardar la noticia.");
        }

    }

    /**
     * Ancla una noticia en la base de datos.
     *
     * @param noticia Noticia a anclar.
     * @throws PersistenciaException Si ocurre un error al anclar la noticia.
     */
    @Override
    public void anclarNoticia(Noticia noticia) throws PersistenciaException {
        Bson filtro = Filters.eq("codigo", noticia.getCodigo());
        Bson actualizar;
        actualizar = Updates.set("destacada", noticia.isDestacada());
        noticias.updateOne(filtro, actualizar);
    }

    /**
     * Busca una noticia en la base de datos.
     *
     * @param noticia Noticia a buscar.
     * @return La noticia encontrada o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error al buscar la noticia.
     */
    @Override
    public Noticia buscarNoticia(Noticia noticia) throws PersistenciaException {
        try {
            Noticia _noticia = noticias.find(Filters.eq("codigo", noticia.getCodigo())).first();
            return _noticia;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar la noticia");
        }
    }

    /**
     * Busca noticias en la base de datos según los filtros proporcionados.
     *
     * @param filtros Filtros para la búsqueda.
     * @return La lista de noticias encontradas.
     * @throws PersistenciaException Si ocurre un error al buscar las noticias.
     */
    @Override
    public List<Noticia> buscarNoticias(FiltroNoticia filtros) throws PersistenciaException {
        List<Bson> pipeline = new ArrayList<>();

        // Lookup siempre presente
        List<Bson> condiciones = new ArrayList<>();

        // Filtro por descripción usando regex
        if (filtros.getTitulo() != null) {
            condiciones.add(
                    regex("contenido.descripcion",
                            ".*" + Pattern.quote(filtros.getTitulo()) + ".*",
                            "i")
            );
        }
        System.out.println(filtros.getCategoria());
        if (filtros.getCategoria() != null) {
            pipeline.add(match(eq("categoria", filtros.getCategoria())));
        }

        if (filtros.esPaginaInicio()) {
            pipeline.add(match(eq("destacada", filtros.isDestacada())));
            pipeline.add(Aggregates.sort(Sorts.descending("fechaCreacion")));
        }

        // Filtro por rango de fechas
        if (filtros.getFechaDesde() != null && filtros.getFechaHasta() != null) {
            Date fechaDesde = ajustarFechaInicio(filtros.getFechaDesde());
            Date fechaHasta = ajustarFechaFin(filtros.getFechaHasta());

            condiciones.add(
                    and(
                            gte("fecha_publicacion", fechaDesde),
                            lte("fecha_publicacion", fechaHasta)
                    )
            );
        }

        // Si hay condiciones, agregar match
        if (!condiciones.isEmpty()) {
            pipeline.add(match(and(condiciones)));
        }

        // Project stage
        return noticias
                .aggregate(pipeline, Noticia.class)
                .into(new ArrayList<>());
    }

    /**
     * Actualiza una noticia existente en la colección de noticias con la
     * información del objeto del parámetro.
     *
     * @param noticia Noticia con el número de post de la noticia a actualizar y
     * la información actualizada.
     * @throws PersistenciaException Si ocurre un error al hacer la
     * actualización en la colección.
     */
    @Override
    public void actualizarNoticia(Noticia noticia) throws PersistenciaException {
        Bson filtro = Filters.eq("codigo", noticia.getCodigo());
        Bson actualizar = null;

        // Verificar si la imagen ha cambiado
        actualizar = Updates.combine(
                Updates.set("ultimaModificacion", new Date()),
                Updates.set("destacada", noticia.isDestacada()),
                Updates.set("contenido", noticia.getContenido()),
                Updates.set("titulo", noticia.getTitulo()),
                Updates.set("imagen", noticia.getImagen())
        );

        noticias.updateOne(filtro, actualizar);
    }

    /**
     * Método para ajustar la fecha de inicio a las 00:00:00
     *
     * @param fecha Fecha a ajustar.
     * @return La fecha con el horario iniciando el dia.
     */
    private Date ajustarFechaInicio(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Método para ajustar la fecha de fin a las 23:59:59
     *
     * @param fecha Fecha a ajustar.
     * @return La fecha con el horario terminado el dia.
     */
    private Date ajustarFechaFin(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Busca todas las noticias en la colección de noticias.
     *
     * @return Una lista de noticias.
     */
    @Override
    public List<Noticia> buscarNoticias() {
        List<Bson> pipeline = new ArrayList<>();

        return noticias
                .aggregate(pipeline, Noticia.class)
                .into(new ArrayList<>());
    }

    @Override
    public List<Noticia> buscarNoticiasDestacadas() {
        List<Bson> pipeline = new ArrayList<>();

        pipeline.add(match(eq("destacada", true)));

        pipeline.add(Aggregates.sort(Sorts.descending("fechaPublicacion")));

        return noticias
                .aggregate(pipeline, Noticia.class)
                .into(new ArrayList<>());
    }

    private String generarNumeroAleatorio() {
        Random random = new Random();

        long numero = random.nextLong() % 10000000000L; // Limita el rango a 10 cifras

        // Formatea el número a una cadena de 10 caracteres, rellenando con ceros a la izquierda
        return "N" + String.format("%010d", Math.abs(numero)); // Usar Math.abs para evitar números negativos
    }

    /**
     * Elimina una noticia de la colección de noticias.
     *
     * @param noticia Noticia con el número de post de la noticia a eliminar.
     */
    @Override
    public void eliminarNoticia(Noticia noticia) {
        Bson filtro = Filters.eq("numPost", noticia.getCodigo());
        System.out.println("Se elimino la noticia: " + noticia.getCodigo());
        noticias.deleteOne(filtro);
    }

}
