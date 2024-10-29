package daos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.result.InsertOneResult;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Contenido;
import entidades.FiltroNoticia;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author karim
 */
public class NoticiaDAO implements INoticiaDAO {

    private IConexion conexion;
    private MongoCollection<Noticia> noticias;
    private MongoCollection<Contenido> contenidos;

    public NoticiaDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        noticias = baseDatos.getCollection("noticias", Noticia.class);
        contenidos = baseDatos.getCollection("contenidos", Contenido.class);
    }

    @Override
    public void publicarNuevaNoticia(Noticia noticia, Contenido contenido) throws PersistenciaException {
        try {
            InsertOneResult resultado = contenidos.insertOne(contenido);
            ObjectId id = resultado.getInsertedId().asObjectId().getValue();
            noticia.setIdContenido(id);
            noticias.insertOne(noticia);
        } catch (MongoException me) {
            throw new PersistenciaException("No se pudo guardar la noticia.");
        }

    }

    @Override
    public void anclarNoticia() throws PersistenciaException {
    }

    @Override
    public Noticia buscarNoticia(Noticia noticia) throws PersistenciaException {
        return null;
    }

    @Override
    public List<Noticia> buscarNoticias(FiltroNoticia filtros) throws PersistenciaException {
        List<Bson> pipeline = new ArrayList<>();

        // Lookup siempre presente
        pipeline.add(lookup("contenidos", "idContenido", "_id", "contenido"));
        pipeline.add(unwind("$contenido"));

        List<Bson> condiciones = new ArrayList<>();
        
        // Filtro por descripción usando regex
        if (filtros.getTitulo()!= null) {
            condiciones.add(
                regex("contenido.descripcion", 
                      ".*" + Pattern.quote(filtros.getTitulo()) + ".*", 
                      "i")
            );
        }


        if (filtros.getCategoria() != null) {
            pipeline.add(match(eq("categoria", filtros.getCategoria())));
        }
        
        // Filtro por rango de fechas
        if (filtros.getFechaDesde()!= null && filtros.getFechaHasta() != null) {
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
        pipeline.add(project(fields(
                include("_id", "anclada", "categoria", "fecha_publicacion", "contenido")
        )));

        return noticias
                .aggregate(pipeline, Noticia.class)
                .into(new ArrayList<>());
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